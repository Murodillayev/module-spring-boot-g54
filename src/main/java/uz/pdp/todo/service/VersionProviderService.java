package uz.pdp.todo.service;

import org.springframework.stereotype.Service;
import uz.pdp.todo.model.entity.ProjectDatabase;
import uz.pdp.todo.model.entity.ProjectDatabaseUser;

import java.util.Optional;

@Service
public class VersionProviderService {

    public Integer getMaxVersion(ProjectDatabase database) {
        Optional<Integer> max = database.getMembers()
                .stream()
                .map(ProjectDatabaseUser::getVersion)
                .max(Integer::compareTo);
        int maxVersion = 1;
        maxVersion += max.orElse(0);
        return maxVersion;
    }
}
