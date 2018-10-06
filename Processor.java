import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Processor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (!roundEnv.processingOver()) {
      try {
        FileObject resource = processingEnv.getFiler().createSourceFile("AbstractClass");
        PrintWriter ps = new PrintWriter(resource.openWriter());
        ps.println(
            "package test;\n" +
            "public abstract class AbstractClass { \n" +
                "public static String helloWorld() { return \"Hello World\"; }\n" +
                "public static final String HELLO_WORLD = \"Hello World\";\n" +
                "}");
        ps.close();
      } catch (IOException e) {
        // Ignore, it may throw resource already exist exception which is not matter
      }
    }
    return false;
  }
}
