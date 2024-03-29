package softuni.exam.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(MappingContext<String, LocalTime> mappingContext) {

                return LocalTime.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            }
        });

        modelMapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {

                return LocalDateTime.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        });
        return modelMapper;
    }


}
