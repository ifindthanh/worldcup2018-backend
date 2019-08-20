package vn.com.smvn.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.com.smvn.model.MathResult.MathResultBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagingFixtures {
    private List<Fixture> fixtures = new ArrayList<>();
    private List<FixturesByDay> fixturesByDay = new ArrayList<FixturesByDay>();
    private boolean hasMoreElement;
    private int nextIndex;
}
