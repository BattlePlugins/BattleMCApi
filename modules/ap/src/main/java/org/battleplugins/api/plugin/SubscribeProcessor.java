package org.battleplugins.api.plugin;

import org.battleplugins.api.event.Subscribe;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SubscribeProcessor extends AbstractProcessor {

    private Set<String> eventClasses = new HashSet<>();
    private Path outputPath;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        String outputFile = processingEnv.getOptions().get("metadataOutputFile");
        if (outputFile != null && !outputFile.isEmpty()) {
            this.outputPath = Paths.get(outputFile);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            if (!roundEnv.errorRaised()) {
                complete();
            }

            return false;
        }

        if (!contains(annotations, Subscribe.class))
            return false;

        for (Element element : roundEnv.getElementsAnnotatedWith(Subscribe.class)) {
            if (element.getKind() != ElementKind.METHOD) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Invalid element type " + element.getKind() + " annotated with @Subscribe. Please make sure everything is correct!", element);
                continue;
            }

            Subscribe sub = element.getAnnotation(Subscribe.class);
            if (!sub.autoIdentifiable())
                continue;

            eventClasses.add(element.getEnclosingElement().asType().toString());
        }

        return true;
    }

    public void complete() {
        try (BufferedWriter writer = createEventWriter()) {
            for (String eventClass : eventClasses) {
                writer.write(eventClass);
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Subscribe.class.getCanonicalName());
        return annotations;
    }

    public boolean contains(Collection<? extends TypeElement> elements, Class<?> clazz) {
        if (elements.isEmpty()) {
            return false;
        }

        final String name = clazz.getName();
        for (TypeElement element : elements) {
            if (element.getQualifiedName().contentEquals(name)) {
                return true;
            }
        }

        return false;
    }

    private BufferedWriter createEventWriter() throws IOException {
        if (this.outputPath != null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing event.properties to " + this.outputPath);
            return Files.newBufferedWriter(this.outputPath);
        }

        FileObject obj = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "event.properties");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing event metadata to " + obj.toUri());
        return new BufferedWriter(obj.openWriter());
    }
}
