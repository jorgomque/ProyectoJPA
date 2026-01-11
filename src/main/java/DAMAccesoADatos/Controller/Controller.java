package DAMAccesoADatos.Controller;




import DAMAccesoADatos.entity.Assistant;
import DAMAccesoADatos.entity.Kid;
import DAMAccesoADatos.entity.Letter;
import DAMAccesoADatos.service.AssistantService;
import DAMAccesoADatos.service.GiftService;
import DAMAccesoADatos.service.KidService;
import  DAMAccesoADatos.view.ConsoleView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Controller {

    private final ConsoleView view;
    private final GiftService giftService;
    private final AssistantService assistantService;
    private final KidService kidService;

    public Controller(ConsoleView view, GiftService giftService, AssistantService assistantService, KidService kidService) {
        this.view = view;
        this.giftService = giftService;
        this.assistantService = assistantService;
        this.kidService = kidService;
    }

    public void run() {
        int op;
        do {
            op = view.menu();
            try {
                switch (op) {
                    case 1 -> createKid();
                    case 2 -> createGift();
                    case 3 -> updateGift();
                    case 4 -> createAssistant();
                    case 5 -> deleteAssistant();
                    case 6 -> showGiftsByCity();
                    case 7 -> showGIftByChars();
                    case 8 -> view.info("Adeu!");

                    default -> view.error("Opción inválida, inténtalo de nuevo.");
                }
            } catch (RuntimeException e) {
                view.error("Error inesperado: " + e.getMessage());
            }
        } while (op != 8);
    }

    public void createKid(){
        if (assistantService.listAll().isEmpty()){
            view.info("No hi ha assistents, tria a donar 1 d'alta");
            return;
        }
        // kid table
        String kidName = view.askString("Nom del xiquet");
        String kidSurname = view.askString("Cognom del xiquet");
        //Letter table
        String address = view.askString("Direcció del xiquet");
        String city = view.askString("Ciutat del xiquet");
        int day = 0;
        while (day != 24 && day != 25){
            day = view.askInt("Introduix el dia (24 0 25)");
        }
        String hour = view.askString("Introduix l'hora");

        //Assitent
        Assistant assistant = null;
        while (assistant == null){
            view.showAssistants(assistantService.listAll());
            int assistantId = view.askInt("ID de l'assistent asignat");
            assistant = assistantService.listById(assistantId);
            if (assistant == null){
                view.info("L'assistent no s'ha trobat, torna a intentar");
            }
        }
        Kid kid = new Kid(kidName, kidSurname);
        Letter letter = new Letter(address, city, hour, day, assistant);
        letter.setKid(kid);

        //Adding gifts
        List<Integer> giftsId =new ArrayList<>();
        List<Integer> giftsQuantities =new ArrayList<>();
        while (true){
            view.showGifts(giftService.listAll());
            int giftOption = view.askInt("Quin regal vols (Introduix l'id o 0 si no vols mes)");
            if (giftOption == 0){
                if (!giftsId.isEmpty()){
                    break;
                }
                view.info("La llista de regla cal tindre reglas");
                continue;
            }
            if (giftsId.contains(giftOption) ){
                view.info("Ja has triat aquest regal");
                continue;
            }

                giftsId.add(giftOption);
                int giftQuantity = view.askInt("Quants vols d'aquest regal?");
                giftsQuantities.add(giftQuantity);
        }
        kidService.createKid(kid, letter, giftsId, giftsQuantities);
        view.info("S'ha guardat l'informació del xiquet amb la seua carta");


    }
    public void createGift(){
        String name = view.askString("nom");
        String reference = view.askString("Referencia");
        int minAge = view.askInt("Edat minima");
        String description = view.askString("Descripció");
        giftService.createGift(name, reference, minAge, description);
        view.info("El regal se ha creat exitosament");
    }
    public void updateGift(){
        view.showGifts(giftService.listAll());
        int giftId = view.askInt("Quin regal vols modficar (inseerta l'id)");
        if (giftService.listById(giftId) == null){
            view.info("El regal no es troba");
            return;
        }
        int updateOption = view.askInt("Que vols modificar \n 1: edat minima \n 2: Descripció \n 3: res ");
            switch (updateOption){
                case 1:
                    int newAge = view.askInt("Introduix la nova edat minima");
                    giftService.updateGiftAge(giftId, newAge);
                    break;
                case 2:

                    String newDescription = view.askString("Introduix la nova descripció");
                    giftService.updateGiftDescription(giftId, newDescription);
                    break;
                case 3:
                    break;

                default:
                    view.info("Opcion invalida");
            }
    }


    public void createAssistant(){
        String name = view.askString("nom");
        assistantService.createAssistant(name);
        view.info("El assistent ha sigut creat exitosament");

    }
    public void deleteAssistant(){
        view.showAssistants(assistantService.listAll());
        int assistantId  = view.askInt("Introduix l'id del assistent que vols eliminar");
        if (assistantService.listById(assistantId) == null){
            view.info("No s'ha trobat l'assistent");
            return;
        }
        assistantService.deleteAssistant(assistantId);
        view.info("L'assistent s'ha eliminat existosament");

    }

    public void showGiftsByCity(){
        String city = view.askString("Introduix el nom de la ciutat");
        view.showGifts(giftService.listByCity(city));

    }

    public void showGIftByChars(){
        String chars = "";
        while (chars.length() < 3){
            chars = view.askString("Introduix 3 caracters");
        }
        view.showGifts(giftService.listByString(chars));

    }
}

