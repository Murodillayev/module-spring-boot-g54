package uz.pdp.todo.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import uz.pdp.todo.AppProps;
import uz.pdp.todo.AuthUser;
import uz.pdp.todo.events.UserCreateEvent;
import uz.pdp.todo.service.EmailService;
import uz.pdp.todo.service.MvRefresherService;

@Service
@RequiredArgsConstructor
public class AuthUserConsumerService {
    private final MvRefresherService mvRefresherService;
    private final EmailService emailService;
    private final AppProps appProps;


    //    @EventListener(UserCreateEvent.class)
    @TransactionalEventListener
    @Order(3)
    @Async
    public void sendNotifyToUser(UserCreateEvent event) {
        AuthUser authUser = event.getAuthUser();
        emailService.sendMessage(authUser.getEmail(), "You register successfully!");
    }

    //    @EventListener(UserCreateEvent.class)
    @TransactionalEventListener
    @Order(2)
    @Async
    public void sendNotifyToModerator(UserCreateEvent event) {
        AuthUser authUser = event.getAuthUser();
        emailService.sendMessage(appProps.getModeratorEmail(), "New user registered successfully : User => %s".formatted(authUser));

    }

    //    @EventListener(UserCreateEvent.class)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    @Order(1)
    @Async
    public void refreshMaterializedView(UserCreateEvent event) {
        mvRefresherService.refreshUsersStatistic();
    }

}


//1
// Authentication(load from db) -> username,password -> jwt_token [access(username, 20 min), refresh(username, 30 kun)]  {ishonchli}
// Authentication(load from db) -> refresh -> jwt_token [access(username, 20 min), refresh(username, 30 kun)]  {ishonchli}

//sync db
// Authorization -> jwt_token -> [ get username from token -> load user from db -> make userdetail -> put security contex ]


//2
// Authentication -> username,password -> jwt_token [access(username, roles, ....), refresh(username)] {tez}
// Authentication -> refresh -> jwt_token [access(username, roles, ....), refresh(username)] {tez}
// no sync db
// Authorization -> jwt_token -> [get {username, roles, ....} from token -> make userdetail -> put security contex  ]

// 0 min , sekin
// 0 - 5 min , tez