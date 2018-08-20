package com.scheible.gwt.reduced.server;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.Lists;
import com.scheible.gwt.reduced.server.framework.collection.JavaArray;
import com.scheible.gwt.reduced.server.framework.rest.ResponseResult;
import com.scheible.gwt.reduced.shared.ColumnDefinitionDto;
import com.scheible.gwt.reduced.shared.Currency;
import com.scheible.gwt.reduced.shared.GridDataDto;
import com.scheible.gwt.reduced.shared.Iso8601DateTimeDto;
import com.scheible.gwt.reduced.shared.MoneyAmountDto;
import com.scheible.gwt.reduced.shared.TitleDto;
import com.scheible.gwt.reduced.shared.TestService;
import com.scheible.gwt.reduced.shared.framework.rest.RestResult;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sj
 */
@RestController
class TestController implements TestService {

    @Override
    @GetMapping("/title")
    public RestResult<TitleDto> getTitle() {
        return new ResponseResult<>(HttpStatus.OK, TitleDto.create("Data from the Server!", Iso8601DateTimeDto.create("12:50")));
    }

    @Override
    @GetMapping("/grid-data")
    public RestResult<GridDataDto> getGridData() {
        return new ResponseResult<>(HttpStatus.OK, GridDataDto.create(
                new JavaArray(Lists.newArrayList(
                        ColumnDefinitionDto.create(Integer.class, "Number"),
                        ColumnDefinitionDto.create(String.class, "Text"),
                        ColumnDefinitionDto.create(Iso8601DateTimeDto.class, "Date/Time"),
                        ColumnDefinitionDto.create(MoneyAmountDto.class, "Money Amount"))),
                new JavaArray(Lists.newArrayList(
                        new JavaArray(Lists.newArrayList(100, ":-)", Iso8601DateTimeDto.create("2001-07-04T12:08:56.235-0700"), MoneyAmountDto.create(new BigDecimal(200), Currency.EUR))),
                        new JavaArray(Lists.newArrayList(200, ";-)", Iso8601DateTimeDto.create("2017-09-24T20:09:24+00:00"), MoneyAmountDto.create(new BigDecimal(120000), Currency.USD)))))));
    }

    public static class Episode {

        public String overallNo;
        public String noInSeason;
        public String title;
        public String directedBy;
        public String writtenBy;
        public String originalAirDate;
        public String prodCode;
        public String usViewersInMillions;
    }

    @GetMapping("/simpsons")
    public GridDataDto getSimpsonsEpisodes() {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';').withQuoteChar('"');
            CsvMapper mapper = new CsvMapper();
            try (InputStream inputStream = new ClassPathResource("simpsons.csv", this.getClass()).getInputStream()) {
                MappingIterator<Episode> reader
                        = mapper.readerFor(Episode.class).with(bootstrapSchema).readValues(inputStream);
                JavaArray episodes = new JavaArray(reader.readAll().stream().map(e
                        -> new JavaArray(Lists.newArrayList(e.overallNo, e.noInSeason, e.title, e.directedBy,
                                e.writtenBy, e.originalAirDate, e.prodCode, e.usViewersInMillions))).collect(Collectors.toList()));
                return GridDataDto.create(
                        new JavaArray(Lists.newArrayList(
                                ColumnDefinitionDto.create(String.class, "No. overall"),
                                ColumnDefinitionDto.create(String.class, "No. in season"),
                                ColumnDefinitionDto.create(String.class, "Title"),
                                ColumnDefinitionDto.create(String.class, "Directed by"),
                                ColumnDefinitionDto.create(String.class, "Written by"),
                                ColumnDefinitionDto.create(String.class, "Original air date"),
                                ColumnDefinitionDto.create(String.class, "Prod. code"),
                                ColumnDefinitionDto.create(String.class, "U.S. viewers (millions)"))),
                        episodes);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Error occurred while loading 'simpsons.csv'.", ex);
        }
    }
}
