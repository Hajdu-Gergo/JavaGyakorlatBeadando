package com.example.beadando;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Locale;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    /*@GetMapping("/home")
    public String user(Model model) {
        return "user";
    }*/
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
        return "admin";
    }

    // Diákok adatai
    private String diakokAdatai() {
        StringBuilder str = new StringBuilder();
        str.append("<h1>Diákok adatai</h1>");
        str.append("<table class=\"table table-warning table-striped w-25 mx-auto\">");
        str.append("<thead><tr><th scope=\"col\">Azonosító</th><th scope=\"col\">Név</th><th scope=\"col\">Születési idő</th></tr><thead><tbody>");
        for (Diak diak : diakRepo.findAll()) {
            str.append("<tr>");
            str.append("<th scope=\"row\">");
            str.append(diak.getDiakaz()).append("</th><td>")
                    .append(diak.getNev()).append("</td><td>")
                    .append(diak.getSzulido()).append("</td>");
            str.append("</tr>");
        }
        str.append("</tbody></table>");
        return str.toString();
    }

    // Munkák adatai
    @GetMapping("/munkak")
    public String munkakAdatai(Model model) {
        String str = munkakAdatok();
        model.addAttribute("str", str);
        return "admin";
    }

    private String munkakAdatok() {
        StringBuilder str = new StringBuilder();
        str.append("<h1>Munkák adatai</h1>");
        str.append("<table class=\"table table-warning table-striped w-50 mx-auto\">");
        str.append("<thead><tr><th scope=\"col\">Név</th><th scope=\"col\">Munkahely</th><th scope=\"col\">Állás</th><th scope=\"col\">Dátum</th><th scope=\"col\">Óradíj</th><th scope=\"col\">Óraszám</th><th scope=\"col\">Foglalkoztatás típusa</th></tr><thead><tbody>");
        for (Munka munka : munkaRepo.findAll()) {
            str.append("<tr>");
            str.append("<th scope=\"row\">");
            str.append(munka.getDiak().getNev()).append("</th><td>")
                    .append(munka.getMhely().getNev()).append("</td><td>")
                    .append(munka.getAllas()).append("</td><td>")
                    .append(munka.getDatum()).append("</td><td>")
                    .append(munka.getOradij()).append("</td><td>")
                    .append(munka.getOraszam()).append("</td><td>")
                    .append(munka.getKozepiskolas() ? "Középiskolás" : "Felnőtt")
                    .append("</td>");
            str.append("</tr>");
        }
        str.append("</tbody></table>");
        return str.toString();
    }

    // Munkahelyek adatai
    @GetMapping("/home")
    public String munkahelyekAdatai(Model model) {
        String str = munkahelyekAdatok();
        model.addAttribute("str", str);
        return "user";
    }

    private String munkahelyekAdatok() {
        StringBuilder str = new StringBuilder();

        str.append("<div class=\"rounded-5\"><table class=\"table table-dark table-striped w-50 mx-auto\">");
        str.append("<thead><tr><th scope=\"col\">Azonosító</th><th scope=\"col\">Név</th><th scope=\"col\">Település</th></tr><thead><tbody>");
        for (Munkahely munkahely : munkahelyRepo.findAll()) {
            str.append("<tr>");
            str.append("<th scope=\"row\">");
            str.append(munkahely.getMhelyid()).append("</th><td>")
                    .append(munkahely.getNev()).append("</td><td>")
                    .append(munkahely.getTelepules()).append("</td>");
            str.append("</tr>");
        }
        str.append("</tbody></table></div>");
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

    @Autowired
    private KapcsolatRepository kapcsolatRepository;

    // Az űrlap megjelenítése
    @GetMapping("/kapcsolat")
    public String kapcsolatForm() {
        return "kapcsolat"; // Ez a kapcsolat.html fájl a resources/templates mappában van
    }

    // Az adatok fogadása és mentése
    @PostMapping("/kapcsolat")
    public String submitKapcsolat(
            @RequestParam("nev") String nev,
            @RequestParam("uzenet") String uzenet) {

        Kapcsolat kapcsolat = new Kapcsolat();
        kapcsolat.setNev(nev);
        kapcsolat.setUzenet(uzenet);
        kapcsolat.setDatum(LocalDateTime.now());

        if(kapcsolat.getNev().isEmpty() || kapcsolat.getUzenet().isEmpty())
            return "redirect:/kapcsolat?error";
        // Mentés az adatbázisba
        kapcsolatRepository.save(kapcsolat);

        return "redirect:/kapcsolat?success";
    }

    @GetMapping("/uzenetek")
    public String uzenetek(Model model) {
        String str = uzenetekAdatok();
        model.addAttribute("str", str);
        return "admin";
    }

    private String uzenetekAdatok() {
        StringBuilder str = new StringBuilder();
        str.append("<h1>Üzenetek</h1>");
        str.append("<table class=\"table table-warning table-striped w-50 mx-auto\">");
        str.append("<thead><tr><th scope=\"col\">Név</th><th scope=\"col\">Üzenet</th><th scope=\"col\">Dátum</th></tr><thead><tbody>");
        for (Kapcsolat kapcsolat : kapcsolatRepository.findAll()) {
            str.append("<tr>");
            str.append("<th scope=\"row\">");
            str.append(kapcsolat.getNev()).append("</th><td>")
                    .append(kapcsolat.getUzenet()).append("</td><td>")
                    .append(kapcsolat.getDatum()).append("</td>");
            str.append("</tr>");
        }
        str.append("</tbody></table>");
        return str.toString();
    }


}


