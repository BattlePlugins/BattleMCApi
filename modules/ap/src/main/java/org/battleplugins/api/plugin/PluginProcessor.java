package org.battleplugins.api.plugin;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtNewConstructor;
import javassist.LoaderClassPath;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.ArrayMemberValue;
import javassist.bytecode.annotation.StringMemberValue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
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

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PluginProcessor extends AbstractProcessor {

    private String mainClass;
    private PluginProperties plugin;

    public static final String BUKKIT_PLUGIN_PATH = "org.battleplugins.api.bukkit.plugin.BukkitPlugin";
    public static final String NUKKIT_PLUGIN_PATH = "org.battleplugins.api.nukkit.plugin.NukkitPlugin";
    public static final String SPONGE_PLUGIN_PATH = "org.battleplugins.api.sponge.plugin.SpongePlugin";

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

        if (!contains(annotations, PluginProperties.class))
            return false;

        for (Element element : roundEnv.getElementsAnnotatedWith(PluginProperties.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Invalid element type " + element.getKind() + " annotated with @PluginProperties. Please make sure everything is correct!", element);
                continue;
            }

            TypeElement pluginElement = (TypeElement) element;
            this.mainClass = pluginElement.getQualifiedName().toString();
            this.plugin = pluginElement.getAnnotation(PluginProperties.class);
        }

        return false;
    }

    public void complete() {
        try (BufferedWriter writer = createPluginWriter()) {
            writer.write("main=" + mainClass);
            writer.newLine();
            writer.write("id=" + plugin.id());
            writer.newLine();
            writer.write("name=" + plugin.name());
            writer.newLine();
            writer.write("version=" + plugin.version());
            writer.newLine();
            writer.write("description=" + plugin.description());
            writer.newLine();
            writer.write("url=" + plugin.url());
            writer.newLine();
            writer.write("authors=" + Arrays.asList(plugin.authors()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter writer = createBukkitWriter()) {
            writer.write("main: " + BUKKIT_PLUGIN_PATH);
            writer.newLine();
            writer.write("name: " + plugin.name());
            writer.newLine();
            writer.write("version: " + plugin.version());
            writer.newLine();
            writer.write("api-version: 1.13");
            writer.newLine();
            if (plugin.authors().length > 0) {
                writer.write("authors: " + Arrays.asList(plugin.authors()));
                writer.newLine();
            }
            if (!plugin.description().isEmpty()) {
                writer.write("description: " + plugin.description());
                writer.newLine();
            }
            if (!plugin.url().isEmpty()) {
                writer.write("website: " + plugin.url());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedWriter writer = createNukkitWriter()) {
            writer.write("main: " + NUKKIT_PLUGIN_PATH);
            writer.newLine();
            writer.write("name: " + plugin.name());
            writer.newLine();
            writer.write("version: " + plugin.version());
            writer.newLine();
            writer.write("api: [1.0.0]");
            writer.newLine();
            if (plugin.authors().length > 0) {
                writer.write("authors: " + Arrays.asList(plugin.authors()));
                writer.newLine();
            }
            if (!plugin.description().isEmpty()) {
                writer.write("description: " + plugin.description());
                writer.newLine();
            }
            if (!plugin.url().isEmpty()) {
                writer.write("website: " + plugin.url());
                writer.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(new LoaderClassPath(getClass().getClassLoader()));

            CtClass ctSponge = pool.makeClass("org.battleplugins.api.sponge.plugin.SpongePlugin");
            ctSponge.setSuperclass(pool.makeClass("org.battleplugins.api.sponge.plugin.AbstractSpongePlugin"));
            ctSponge.addConstructor(CtNewConstructor.make(null, null, CtNewConstructor.PASS_PARAMS, null, null, ctSponge));
            ClassFile classFile = ctSponge.getClassFile();
            ConstPool cPool = classFile.getConstPool();

            AnnotationsAttribute attribute = new AnnotationsAttribute(cPool, AnnotationsAttribute.visibleTag);
            Annotation annotation = new Annotation("org.spongepowered.api.plugin.Plugin", cPool);
            annotation.addMemberValue("id", new StringMemberValue(plugin.id(), cPool));
            annotation.addMemberValue("name", new StringMemberValue(plugin.name(), cPool));
            annotation.addMemberValue("version", new StringMemberValue(plugin.version(), cPool));
            annotation.addMemberValue("description", new StringMemberValue(plugin.description(), cPool));
            annotation.addMemberValue("url", new StringMemberValue(plugin.url(), cPool));

            StringMemberValue[] members = new StringMemberValue[plugin.authors().length];
            for (int i = 0; i < plugin.authors().length; i++) {
                members[i] = new StringMemberValue(plugin.authors()[i], cPool);
            }

            ArrayMemberValue arrayMemberValue = new ArrayMemberValue(cPool);
            arrayMemberValue.setValue(members);

            annotation.addMemberValue("authors", arrayMemberValue);


            attribute.addAnnotation(annotation);
            classFile.addAttribute(attribute);

            writeClassToRoot(ctSponge);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private BufferedWriter createPluginWriter() throws IOException {
        if (this.outputPath != null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing MCPlugin plugin.properties to " + this.outputPath);
            return Files.newBufferedWriter(this.outputPath);
        }

        FileObject obj = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "plugin.properties");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing plugin metadata to " + obj.toUri());
        return new BufferedWriter(obj.openWriter());
    }

    private BufferedWriter createBukkitWriter() throws IOException {
        if (this.outputPath != null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing Bukkit plugin.yml to " + this.outputPath);
            return Files.newBufferedWriter(this.outputPath);
        }

        FileObject obj = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "plugin.yml");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing plugin metadata to " + obj.toUri());
        return new BufferedWriter(obj.openWriter());
    }

    private BufferedWriter createNukkitWriter() throws IOException {
        if (this.outputPath != null) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing Nukkit nukkit.yml to " + this.outputPath);
            return Files.newBufferedWriter(this.outputPath);
        }

        FileObject obj = this.processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", "nukkit.yml");
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing plugin metadata to " + obj.toUri());
        return new BufferedWriter(obj.openWriter());
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(PluginProperties.class.getCanonicalName());
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

    private void writeClassToRoot(CtClass ctClass) throws CannotCompileException, IOException {
        FileObject file = processingEnv.getFiler().getResource(StandardLocation.CLASS_OUTPUT, "", "root");
        ctClass.writeFile(new File(file.toUri()).getParent());
    }
}
