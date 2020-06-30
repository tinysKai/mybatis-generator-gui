package com.zzg.mybatis.generator.plugins;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 生成Repository实体类的插件
 * 2020-06-30
 */
public class RepositoryDaoPlugin extends PluginAdapter {
    private ShellCallback shellCallback = null;
    private FullyQualifiedJavaType annotationRepository;
    private String annotation = "@Repository";

    public RepositoryDaoPlugin() {
        shellCallback = new DefaultShellCallback(false);
        annotationRepository = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        String daoTargetDir = context.getJavaClientGeneratorConfiguration().getTargetProject();
        String daoTargetPackage = context.getJavaClientGeneratorConfiguration().getTargetPackage();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<>();
        String javaFileEncoding = context.getProperty("javaFileEncoding");
        //构建类名
        String tableName = context.getTableConfigurations().get(0).getDomainObjectName();
        String repositoryName = tableName + "Repository";
        TopLevelClass repositoryClass = new TopLevelClass(daoTargetPackage + "." + repositoryName);
        //添加公共属性
        repositoryClass.setVisibility(JavaVisibility.PUBLIC);
        //添加引用类
        repositoryClass.addImportedType(annotationRepository);
        //添加引用注解
        repositoryClass.addAnnotation(annotation);




        //生成文件
        GeneratedJavaFile mapperJavafile = new GeneratedJavaFile(repositoryClass, daoTargetDir, javaFileEncoding, javaFormatter);
        try {
            File mapperDir = shellCallback.getDirectory(daoTargetDir, daoTargetPackage);
            File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
            // 文件存在
            if (mapperFile.exists()) {
                mapperFile.delete();
            }
            mapperJavaFiles.add(mapperJavafile);
        } catch (ShellException e) {
            e.printStackTrace();
        }
        return mapperJavaFiles;
    }


    @Override
    public boolean validate(List<String> list) {
        return true;
    }

}
