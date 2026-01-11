package DAMAccesoADatos.app;

import DAMAccesoADatos.Controller.Controller;
import DAMAccesoADatos.service.AssistantService;
import DAMAccesoADatos.service.GiftService;
import DAMAccesoADatos.service.KidService;
import DAMAccesoADatos.view.ConsoleView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projecteNadalPU");
        var consoleView = new ConsoleView();

        GiftService giftService = new GiftService(emf);
        AssistantService assistantService = new AssistantService(emf);
        KidService kidService = new KidService(emf);
        var controller = new Controller(consoleView, giftService, assistantService, kidService );
        controller.run();

    }
}
