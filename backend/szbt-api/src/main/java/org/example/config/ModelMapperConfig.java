package org.example.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;


@Configuration
public class ModelMapperConfig {

    private Converter<Date, String> dateToStringConverter = new AbstractConverter<Date, String>() {
        @Override
        protected String convert(Date date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return date == null ? null : simpleDateFormat.format(date);
        }
    };

    /**
     * 将ModelMapper注册到Spring中
     * 可以添加一些定制化的配置，官方文档：http://modelmapper.org/user-manual/property-mapping/
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // 官方配置说明： http://modelmapper.org/user-manual/configuration/
        // 完全匹配
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);

        // 匹配策略使用严格模式
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(dateToStringConverter);

        return modelMapper;
    }

}