package mc.alk.mc.plugin;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PluginProcessor extends AbstractProcessor {

    private String mainClass;
    private PluginProperties plugin;

    public static final String BUKKIT_PLUGIN_PATH = "mc.alk.bukkit.plugin.BukkitPlugin";
    public static final String NUKKIT_PLUGIN_PATH = "mc.alk.nukkit.plugin.NukkitPlugin";
    public static final String SPONGE_PLUGIN_PATH = "mc.alk.sponge.plugin.SpongePlugin";

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
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Writing Nukkit plugin.yml to " + this.outputPath);
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
}
