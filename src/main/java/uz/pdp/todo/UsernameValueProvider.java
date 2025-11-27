package uz.pdp.todo;

import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ValueProvider;

import java.util.List;

@ShellComponent
public class UsernameValueProvider implements ValueProvider {

    private final AuthUserDao authUserDao;

    public UsernameValueProvider(AuthUserDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    @Override
    public List<CompletionProposal> complete(CompletionContext completionContext) {

        List<AuthUser> all = authUserDao.findAll();
        return all.stream().map(
                a -> new CompletionProposal(a.getUsername())
        ).toList();
    }
}
