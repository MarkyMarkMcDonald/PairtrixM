package pairtrix.domain;

import com.codepoetics.protonpack.StreamUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Application {

    public List<String> teamMembers;

    public Application() {
        this.teamMembers = new ArrayList<>();
    }

    public List<Pairing> pairings() {
        return StreamUtils.aggregate(teamMembers.stream(), 2)
                .map(pairing -> {
                    if (pairing.size() <= 1) {
                        return new Pairing(pairing.get(0), Optional.empty());
                    } else {
                        return new Pairing(pairing.get(0), Optional.of(pairing.get(1)));
                    }
                })
                .collect(toList());
    }

    public void addTeamMember(String name) {
        teamMembers.add(name);
    }
}
