package conf;

import controller.HanoiController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

public class Routes implements ApplicationRoutes {
    @Override
    public void init(Router router) {

        router.POST().route("/newGame/{pegs}/{rods}/").with(HanoiController::newGame);
        router.POST().route("/playGame/{from}/{to}/").with(HanoiController::playGame);

    }

}
