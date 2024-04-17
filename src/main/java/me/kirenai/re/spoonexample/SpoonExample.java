package me.kirenai.re.spoonexample;

import spoon.Launcher;
import spoon.legacy.NameFilter;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.ModifierKind;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class SpoonExample {

    public static void main(String[] args) {
        Launcher launcher = new Launcher();

        launcher.addInputResource("src/main/java/me/kirenai/re/spoonexample/model");

        launcher.buildModel();
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setTabulationSize(4);
        launcher.getEnvironment().useTabulations(true);

        CtModel model = launcher.getModel();

        for (CtClass<?> ctClass : model.getElements(new NameFilter<CtClass<?>>("Person"))) {
            CtField<Date> newField = launcher.getFactory().Field().create(
                    ctClass,
                    new HashSet<>(Collections.singletonList(ModifierKind.PRIVATE)),
                    launcher.getFactory().Type().createReference(Date.class),
                    "creationDate"
            );

            ctClass.addField(newField);

        }

        launcher.setSourceOutputDirectory("src/main/java");
        launcher.process();
        launcher.prettyprint();
    }

}
