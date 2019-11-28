package com.egls.cashloan.hades.generate;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * Mybatis 代码生成公工具
 *
 * @author sunfeilong   (sunfeilong@eglsgame.com)
 * @version V1.0
 * @date 2019/8/17 13:38
 */
public class MybatisPlusGenerateUtil {

    private static final String databaseURL = "jdbc:mysql://localhost:3306/loansms?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static final String userName = "root";
    private static final String password = "root";
    private static String generateUserName = "";

    public static void main(String[] args) {
        generateUserName = "tgw";
        generate(ConfigEnum.CL);
    }
    
    private static String[] tableNames = {"loan_api_channel"};

    public enum ConfigEnum {
        /**
         * 桌面
         */
        CL("F:\\IDEAspace\\tgw-start-parent\\tgw-mybatis-plus",
                "com.tgw.mybatis.tgwmybatis",
                "",
                tableNames
                ),
        
        ;

        /**
         * 项目路径
         */
        private final String projectPath;
        /**
         * 项目包名
         */
        private final String packageName;
        /**
         * 模块儿名
         */
        private final String modelName;
        /**
         * 数据库表明，多个用逗号分隔
         */
        private String[] tables;

        ConfigEnum(String projectPath, String packageName, String modelName, String[] tables) {
            this.projectPath = projectPath;
            this.packageName = packageName;
            this.modelName = modelName;
            this.tables = tables;
        }

        /**
         * 设置生成的表，多表以逗号分隔
         *
         * @param tables 表，多个用逗号分隔
         */
        public void setTables(String[] tables) {
            this.tables = tables;
        }
    }

    /**
     * 根据配置生成Mapper等文件
     *
     * @param generateConfig 初始化配置
     */
    public static void generate(ConfigEnum generateConfig) {
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig config = config(generateConfig);
        mpg.setGlobalConfig(config);
        mpg.setDataSource(datasource());
        PackageConfig packageInfo = packageConfig(generateConfig);
        mpg.setPackageInfo(packageInfo);
        String templatePath = "/templates/mapper.xml.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = generateConfig.projectPath + "/src/main/resources/mapper/" + packageInfo.getModuleName() + "/";
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        InjectionConfig injectionConfig = injectionConfig();
        injectionConfig.setFileOutConfigList(focList);
        mpg.setCfg(injectionConfig);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(generateConfig.tables);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageInfo.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static GlobalConfig config(ConfigEnum generateConfig) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(generateConfig.projectPath + "/src/main/java");
        globalConfig.setAuthor(generateUserName);
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setFileOverride(false);
        globalConfig.setDateType(DateType.TIME_PACK);
        globalConfig.setOpen(false);
        return globalConfig;
    }

    private static DataSourceConfig datasource() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(databaseURL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername(userName);
        dataSourceConfig.setPassword(password);
        return dataSourceConfig;
    }

    private static PackageConfig packageConfig(ConfigEnum generateConfig) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(generateConfig.modelName);
        packageConfig.setParent(generateConfig.packageName);
        return packageConfig;
    }

    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
    }
}
