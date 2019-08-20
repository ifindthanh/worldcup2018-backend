package vn.com.smvn.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.Fixture.FixtureBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixturesByDay {
    private String date;
    private List<Fixture> fixtures = new ArrayList<Fixture>();
}
