package com.example.beadando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/home")
    public String user(Model model) {
        return "user";
    }
    @GetMapping("/admin/home")
    public String admin() {
        return "admin";
    }

    @GetMapping("/regisztral")
    public String greetingForm(Model model) {
        model.addAttribute("reg", new User());
        return "regisztral";
    }

    @Autowired
    private UserRepository userRepo;
    @PostMapping("/regisztral_feldolgoz")
    public String Regisztráció(@ModelAttribute User user, Model model) {
        for(User felhasznalo2: userRepo.findAll())
            if(felhasznalo2.getEmail().equals(user.getEmail())){
                model.addAttribute("uzenet", "A regisztrációs email már foglalt!");
                return "reghiba";
            }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_Vendeg");
        userRepo.save(user);
        model.addAttribute("id", user.getId());
        return "regjo";
    }

    @Autowired
    private DiakRepository diakRepo;

    @Autowired
    private MunkaRepository munkaRepo;

    @Autowired
    private MunkahelyRepository munkahelyRepo;

    // Főoldal: Diákok adatai megjelenítése
    @GetMapping("/diak")
    public String fooldal(Model model) {
        String str = diakokAdatai();
        model.addAttribute("str", str);
        return "index";
    }

    // Diákok adatai
    private String diakokAdatai() {
        StringBuilder str = new StringBuilder();
        for (Diak diak : diakRepo.findAll()) {
            str.append(diak.getDiakaz()).append("; ")
                    .append(diak.getNev()).append("; ")
                    .append(diak.getSzulido()).append("<br>");
        }
        return str.toString();
    }

    // Munkák adatai
    @GetMapping("/munkak")
    public String munkakAdatai(Model model) {
        String str = munkakAdatok();
        model.addAttribute("str", str);
        return "index";
    }

    private String munkakAdatok() {
        StringBuilder str = new StringBuilder();
        for (Munka munka : munkaRepo.findAll()) {
            str.append(munka.getId()).append("; ")
                    .append(munka.getAllas()).append("; ")
                    .append(munka.getDatum()).append("; ")
                    .append(munka.getOradij()).append("; ")
                    .append(munka.getOraszam()).append("; ")
                    .append(munka.getKozepiskolas() ? "Középiskolás" : "Felnőtt")
                    .append("<br>");
        }
        return str.toString();
    }

    // Munkahelyek adatai
    @GetMapping("/munkahelyek")
    public String munkahelyekAdatai(Model model) {
        String str = munkahelyekAdatok();
        model.addAttribute("str", str);
        return "index";
    }

    private String munkahelyekAdatok() {
        StringBuilder str = new StringBuilder();
        for (Munkahely munkahely : munkahelyRepo.findAll()) {
            str.append(munkahely.getMhelyid()).append("; ")
                    .append(munkahely.getNev()).append("; ")
                    .append(munkahely.getTelepules()).append("<br>");
        }
        return str.toString();
    }

    // Egy adott diák munkáinak megjelenítése
    @GetMapping("/diakmunka")
    public String adottDiakMunkai(Model model) {
        String str = adottDiakMunkaiAdatok("Kos Péter");
        model.addAttribute("str", str);
        return "index";
    }

    private String adottDiakMunkaiAdatok(String diakNev) {
        Diak diak = diakRepo.findByNev(diakNev);
        if (diak == null) return "Nincs ilyen diák.";

        StringBuilder str = new StringBuilder();
        str.append("Diák neve: ").append(diak.getNev()).append("<br>");

        for (Munka munka : munkaRepo.findByDiak(diak)) {
            str.append(munka.getAllas()).append("; ")
                    .append(munka.getDatum()).append("; ")
                    .append(munka.getOradij()).append(" Ft/óra<br>");
        }
        return str.toString();
    }
}


