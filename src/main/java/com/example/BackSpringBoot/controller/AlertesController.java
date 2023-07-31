package com.example.BackSpringBoot.controller;

import com.example.BackSpringBoot.model.Article;
import com.example.BackSpringBoot.model.DossierEquivalence;
import com.example.BackSpringBoot.service.AlertesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ipersyst/alerte")
public class AlertesController {
    private final AlertesService alertesService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AlertesController(AlertesService alertesService) {
        this.alertesService = alertesService;
    }
//comp ayant change de stat
    @GetMapping("/compchangedcount/{majFromDate}")
    @ResponseBody
    private Integer getStsCompChangedCount(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a " +
                "WHERE a.art_type_article = 'c' " +
                "AND a.art_followed_component = 'o' " +
                "AND a.art_couleur <> a.art_couleur_precedente " +
                "AND a.art_date_couleur > ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, majFromDate);
        return count;
    }
    // liste
    @GetMapping("/compchangedlist/{majFromDate}")
    @ResponseBody
    private List<Article> getStsCompChangedList(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate) {
        String sql = "SELECT * FROM article a " +
                "WHERE a.art_type_article = 'c' " +
                "AND a.art_followed_component = 'o' " +
                "AND a.art_couleur <> a.art_couleur_precedente " +
                "AND a.art_date_couleur > ?";

        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), majFromDate);
        return articles;
    }
//comp perdant le stat vert
    @GetMapping("/complostgreencount/{majFromDate}")
    @ResponseBody
    private Integer getStsCompLostGreenCount(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a " +
                "WHERE a.art_type_article = 'c' " +
                "AND a.art_followed_component = 'o' " +
                "AND a.art_couleur_precedente = 'vert' " +
                "AND a.art_couleur <> a.art_couleur_precedente " +
                "AND a.art_date_couleur > ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, majFromDate);
        return count;
    }
    //list
    @GetMapping("/complostgreenlist/{majFromDate}")
    @ResponseBody
    private List<Article> getStsCompLostGreenList(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate) {
        String sql = "SELECT * FROM article a " +
                "WHERE a.art_type_article = 'c' " +
                "AND a.art_followed_component = 'o' " +
                "AND a.art_couleur_precedente = 'vert' " +
                "AND a.art_couleur <> a.art_couleur_precedente " +
                "AND a.art_date_couleur > ?";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), majFromDate);
        return articles;
    }
//serie perdant le stat vert
    @GetMapping("/serlostgreencount/{majFromDate}")
    @ResponseBody
    private Integer getStsSerLostGreenCount(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a " +
                "WHERE a.art_type_article = 's' " +
                "AND a.art_followed_component = 'o' " +
                "AND a.art_couleur_precedente = 'vert' " +
                "AND a.art_couleur <> a.art_couleur_precedente " +
                "AND a.art_date_couleur > ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, majFromDate);
        return count;
    }

    //list
    @GetMapping("/serlostgreenlist/{majFromDate}")
    @ResponseBody
    private List<Article> getStsSerLostGreenList(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate) {
        String sql = "SELECT * FROM article a " +
                "WHERE a.art_type_article = 's' " +
                "AND a.art_followed_component = 'o' " +
                "AND a.art_couleur_precedente = 'vert' " +
                "AND a.art_couleur <> a.art_couleur_precedente " +
                "AND a.art_date_couleur > ?";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), majFromDate);
        return articles;
    }

    //deq selon niveau de validation
    @GetMapping("/deq/{nivValidation}")
    @ResponseBody
    private Integer getDeqPerNivValCount(@PathVariable String nivValidation) {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM dossierequivalence deq " +
                "WHERE deq.dsequiv_niveau_validation = ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nivValidation);
        return count;
    }
    //list
    @GetMapping("/deqlist/{nivValidation}")
    @ResponseBody
    private List<DossierEquivalence> getDeqPerNivValList(@PathVariable String nivValidation) {
        String sql = "SELECT * FROM dossierequivalence deq " +
                "WHERE deq.dsequiv_niveau_validation = ? ;";
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), nivValidation);
        return deqs;
    }
    //deq selon niveau de validation
    @GetMapping("/dsh/{nivValidation}")
    @ResponseBody
    private Integer getDshPerNivValCount(@PathVariable String nivValidation) {
        String sql = "SELECT COUNT(DISTINCT dsh.id) FROM dossier_homologation dsh " +
                "WHERE dsh.dsh_niveau_validation = ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nivValidation);
        return count;
    }
    //dsh en attente envoi avec deq selon niveau de validation
    @GetMapping("/dsh/deq-niv-val/{nivValidation}")
    @ResponseBody
    private Integer getStsDshPerDeqCount(@PathVariable String nivValidation) {
        String sql = "SELECT COUNT(DISTINCT dsh.id) FROM dossierequivalence deq, dossier_homologation dsh "+
        "WHERE dsh.dsh_niveau_validation = 'En attente envoi' "+
        "AND dsh.pk_equivalence_id = deq.id "+
        "AND deq.dsequiv_niveau_validation = ? " ;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nivValidation);
        return count;
    }
    //list
    @GetMapping("/dsh/deq-niv-vallist/{nivValidation}")
    @ResponseBody
    private List<DossierEquivalence> getStsDshPerDeqList(@PathVariable String nivValidation) {
        String sql = "SELECT * FROM dossierequivalence deq, dossier_homologation dsh "+
                "WHERE dsh.dsh_niveau_validation = 'En attente envoi' "+
                "AND dsh.pk_equivalence_id = deq.id "+
                "AND deq.dsequiv_niveau_validation = ? " ;
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), nivValidation);
        return deqs;
    }
    //articles classés par type article et statut
    @GetMapping("/articles/typeart/{typeArt}/statut/{statArt}")
    @ResponseBody
    private Integer getArtPerStatAndCompCount(@PathVariable String typeArt, @PathVariable String statArt) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a "+
        "WHERE a.art_type_article = ? "+
        "AND a.art_couleur = ? ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, typeArt, statArt);
        return count;
    }
    //list
    @GetMapping("/articleslist/typeart/{typeArt}/statut/{statArt}")
    @ResponseBody
    private List<Article> getArtPerStatAndCompList(@PathVariable String typeArt, @PathVariable String statArt) {
        String sql = "SELECT * FROM article a "+
                "WHERE a.art_type_article = ? "+
                "AND a.art_couleur = ? ";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), typeArt, statArt);
        return articles;
    }



    //deqs classés par type composant
    @GetMapping("/deqs/typecomp/{typeComp}")
    @ResponseBody
    private Integer getArtPerStatAndCompCount(@PathVariable String typeComp) {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM article a, dossierequivalence deq \n" +
                "        WHERE deq.id_art_init = a.id \n" +
                "        AND deq.dsequiv_niveau_validation = 'En attente proposition' \n" +
                "        AND a.art_type_composant = ? \n" +
                "        AND a.art_followed_component = 'o' ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, typeComp);
        return count;
    }
    //list
    @GetMapping("/deqs/typecomplist/{typeComp}")
    @ResponseBody
    private List<DossierEquivalence> getArtPerStatAndCompList(@PathVariable String typeComp) {
        String sql = "SELECT * FROM article a, dossierequivalence deq \n" +
                "        WHERE deq.id_art_init = a.id \n" +
                "        AND deq.dsequiv_niveau_validation = 'En attente proposition' \n" +
                "        AND a.art_type_composant = ? \n" +
                "        AND a.art_followed_component = 'o' ";
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), typeComp);
        return deqs;
    }

    //deqs ayant perrenité faible
    @GetMapping("/deqs/perrenitefaible")
    @ResponseBody
    private Integer getDeqPerreniteFaible() {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM article a, dossierequivalence deq \n" +
                "                       WHERE deq.id_art_init = a.id \n" +
                "                       AND deq.dsequiv_niveau_validation = 'En attente proposition'\n" +
                "                       AND a.art_couleur = 'vert'\n" +
                "                       AND a.art_followed_component = 'o' \n" +
                "\t\t\t\t\t             AND a.art_cycle_vie = 4";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    //list
    @GetMapping("/deqs/perrenitefaiblelist")
    @ResponseBody
    private List<DossierEquivalence> getDeqPerreniteFaibleList() {
        String sql = "SELECT * FROM article a, dossierequivalence deq \n" +
                "                       WHERE deq.id_art_init = a.id  \n" +
                "                       AND deq.dsequiv_niveau_validation = 'En attente proposition'\n" +
                "                       AND a.art_couleur = 'vert'\n" +
                "                       AND a.art_followed_component = 'o' \n" +
                "\t\t\t\t\t             AND a.art_cycle_vie = 4";
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class));
        return deqs;
    }

    //maj par type de composant
    @GetMapping("/deqs/majpertype/{majFromDate}/type/{artType}")
    @ResponseBody
    private Integer getMajPerTypeComp(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable String artType) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a "+
        "WHERE a.art_couleur = 'vert' "+
        "AND a.art_followed_component = 'o' "+
        "AND a.art_type_composant = ? "+
        "AND a.art_date_couleur > ?  ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, artType, majFromDate);
        return count;
    }

    //liste
    @GetMapping("/deqs/majpertypelist/{majFromDate}/type/{artType}")
    @ResponseBody
    private List<Article> getMajPerTypeCompList(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable String artType) {
        String sql = "SELECT * FROM article a "+
                "WHERE a.art_couleur = 'vert' "+
                "AND a.art_followed_component = 'o' "+
                "AND a.art_type_composant = ? "+
                "AND a.art_date_couleur > ?  ";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), artType, majFromDate);
        return articles;
    }



    ///////////////////////////////////////////////////Get All Per Module/////////////////////////////////////////////////////////////////////

    //comp ayant change de stat
    @GetMapping("/compchangedcount/{majFromDate}/clst/{clst}")
    @ResponseBody
    private Integer getStsCompChangedCountPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a, dossierequivalence deq, dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = 'c'\n" +
                "                AND a.art_followed_component = 'o'\n" +
                "                AND a.art_couleur <> a.art_couleur_precedente\n" +
                "                AND a.art_date_couleur > ? '\n" +
                "\t\t\t\tAND dsh.pk_client_site_id =  ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, majFromDate, clst);
        return count;
    }
    // liste
    @GetMapping("/compchangedlist/{majFromDate}/clst/{clst}")
    @ResponseBody
    private List<Article> getStsCompChangedListPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable Long clst) {
        String sql = "SELECT * FROM article a, dossierequivalence deq, dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = 'c'\n" +
                "                AND a.art_followed_component = 'o'\n" +
                "                AND a.art_couleur <> a.art_couleur_precedente\n" +
                "                AND a.art_date_couleur > ?\n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ? ;";

        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), majFromDate, clst);
        return articles;
    }
    //comp perdant le stat vert
    @GetMapping("/complostgreencount/{majFromDate}/clst/{clst}")
    @ResponseBody
    private Integer getStsCompLostGreenCountPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a, dossierequivalence deq, dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = 'c'\n" +
                "                AND a.art_followed_component = 'o'\n" +
                "\t\t\t\tAND a.art_couleur_precedente = 'vert'\n" +
                "                AND a.art_couleur <> a.art_couleur_precedente\n" +
                "                AND a.art_date_couleur > ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id =  ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, majFromDate, clst);
        return count;
    }
    //list
    @GetMapping("/complostgreenlist/{majFromDate}/clst/{clst}")
    @ResponseBody
    private List<Article> getStsCompLostGreenListPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable Long clst) {
        String sql = "SELECT * FROM article a, dossierequivalence deq, dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = 'c'\n" +
                "                AND a.art_followed_component = 'o'\n" +
                "\t\t\t\tAND a.art_couleur_precedente = 'vert'\n" +
                "                AND a.art_couleur <> a.art_couleur_precedente\n" +
                "                AND a.art_date_couleur > ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ? ;";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), majFromDate, clst);
        return articles;
    }
    //serie perdant le stat vert
    @GetMapping("/serlostgreencount/{majFromDate}/clst/{clst}")
    @ResponseBody
    private Integer getStsSerLostGreenCountPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a, dossierequivalence deq, dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = 's'\n" +
                "                AND a.art_followed_component = 'o'\n" +
                "\t\t\t\tAND a.art_couleur_precedente = 'vert'\n" +
                "                AND a.art_couleur <> a.art_couleur_precedente\n" +
                "                AND a.art_date_couleur > ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, majFromDate, clst);
        return count;
    }

    //list
    @GetMapping("/serlostgreenlist/{majFromDate}/clst/{clst}")
    @ResponseBody
    private List<Article> getStsSerLostGreenListPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable Long clst) {
        String sql = "SELECT * FROM article a, dossierequivalence deq, dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = 's'\n" +
                "                AND a.art_followed_component = 'o'\n" +
                "\t\t\t\tAND a.art_couleur_precedente = 'vert'\n" +
                "                AND a.art_couleur <> a.art_couleur_precedente\n" +
                "                AND a.art_date_couleur > ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ?;";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), majFromDate, clst);
        return articles;
    }

    //deq selon niveau de validation
    @GetMapping("/deq/{nivValidation}/clst/{clst}")
    @ResponseBody
    private Integer getDeqPerNivValCountPerClst(@PathVariable String nivValidation, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM dossierequivalence deq , dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.dsequiv_niveau_validation = ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ? ;";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nivValidation, clst);
        return count;
    }
    //list
    @GetMapping("/deqlist/{nivValidation}/clst/{clst}")
    @ResponseBody
    private List<DossierEquivalence> getDeqPerNivValListPerClst(@PathVariable String nivValidation, @PathVariable Long clst) {
        String sql = "SELECT * FROM dossierequivalence deq , dossier_homologation dsh\n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "\t\t\t\tAND deq.dsequiv_niveau_validation = ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ? ;";
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), nivValidation, clst);
        return deqs;
    }
    //deq selon niveau de validation
    @GetMapping("/dsh/{nivValidation}/clst/{clst}")
    @ResponseBody
    private Integer getDshPerNivValCountPerClst(@PathVariable String nivValidation, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT dsh.id) FROM dossier_homologation dsh " +
                "WHERE dsh.dsh_niveau_validation = ? " +
                "AND dsh.pk_client_site_id = ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nivValidation, clst);
        return count;
    }
    //dsh en attente envoi avec deq selon niveau de validation
    @GetMapping("/dsh/deq-niv-val/{nivValidation}/clst/{clst}")
    @ResponseBody
    private Integer getStsDshPerDeqCountPerClst(@PathVariable String nivValidation, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT dsh.id) FROM dossierequivalence deq, dossier_homologation dsh "+
                "WHERE dsh.dsh_niveau_validation = 'En attente envoi' "+
                "AND dsh.pk_equivalence_id = deq.id "+
                "AND deq.dsequiv_niveau_validation = ? " +
                "AND dsh.pk_client_site_id = ? " ;
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nivValidation, clst);
        return count;
    }
    //list
    @GetMapping("/dsh/deq-niv-vallist/{nivValidation}/clst/{clst}")
    @ResponseBody
    private List<DossierEquivalence> getStsDshPerDeqListPerClst(@PathVariable String nivValidation, @PathVariable Long clst) {
        String sql = "SELECT * FROM dossierequivalence deq, dossier_homologation dsh "+
                "WHERE dsh.dsh_niveau_validation = 'En attente envoi' "+
                "AND dsh.pk_equivalence_id = deq.id "+
                "AND deq.dsequiv_niveau_validation = ? " +
                "AND dsh.pk_client_site_id = ? " ;
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), nivValidation, clst);
        return deqs;
    }
    //articles classés par type article et statut
    @GetMapping("/articles/typeart/{typeArt}/statut/{statArt}/clst/{clst}")
    @ResponseBody
    private Integer getArtPerStatAndCompCountPerClst(@PathVariable String typeArt, @PathVariable String statArt, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a, dossierequivalence deq, dossier_homologation dsh \n" +
                "                WHERE dsh.pk_equivalence_id = deq.id\n" +
                "                AND deq.id_art_init = a.id \n" +
                "\t\t\t\tAND a.art_type_article = ? \n" +
                "                AND a.art_couleur = ? \n" +
                "\t\t\t\tAND dsh.pk_client_site_id = ? ;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, typeArt, statArt, clst);
        return count;
    }
    //list
    @GetMapping("/articleslist/typeart/{typeArt}/statut/{statArt}/clst/{clst}")
    @ResponseBody
    private List<Article> getArtPerStatAndCompListPerClst(@PathVariable String typeArt, @PathVariable String statArt, @PathVariable Long clst) {
        String sql = "SELECT * FROM article a, dossierequivalence deq, dossier_homologation dsh \\n\" +\n" +
                "                \"                WHERE dsh.pk_equivalence_id = deq.id\\n\" +\n" +
                "                \"                AND deq.id_art_init = a.id \\n\" +\n" +
                "                \"\\t\\t\\t\\tAND a.art_type_article = ? \\n\" +\n" +
                "                \"                AND a.art_couleur = ? \\n\" +\n" +
                "                \"\\t\\t\\t\\tAND dsh.pk_client_site_id = ?  ";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), typeArt, statArt, clst);
        return articles;
    }



    //deqs classés par type composant
    @GetMapping("/deqs/typecomp/{typeComp}/clst/{clst}")
    @ResponseBody
    private Integer getArtPerStatAndCompCountPerClst(@PathVariable String typeComp, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM article a, dossierequivalence deq, dossier_homologation dsh \n" +
                "                        WHERE dsh.pk_equivalence_id = deq.id\n" +
                "                        AND deq.id_art_init = a.id \n" +
                "                        AND deq.dsequiv_niveau_validation = 'En attente proposition'\n" +
                "                       AND a.art_type_composant = ? \n" +
                "                       AND a.art_followed_component = 'o'\n" +
                "\t\t\t\t\t   AND dsh.pk_client_site_id = ? ; ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, typeComp, clst);
        return count;
    }
    //list
    @GetMapping("/deqs/typecomplist/{typeComp}/clst/{clst}")
    @ResponseBody
    private List<DossierEquivalence> getArtPerStatAndCompListPerClst(@PathVariable String typeComp, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM article a, dossierequivalence deq, dossier_homologation dsh \n" +
                "                        WHERE dsh.pk_equivalence_id = deq.id\n" +
                "                AND deq.id_art_init = a.id \n" +
                "                        AND deq.dsequiv_niveau_validation = 'En attente proposition'\n" +
                "                       AND a.art_type_composant = ? \n" +
                "                       AND a.art_followed_component = 'o'\n" +
                "\t\t\t\t\t   AND dsh.pk_client_site_id = ?; ";
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), typeComp, clst);
        return deqs;
    }

    //deqs ayant perrenité faible
    @GetMapping("/deqs/perrenitefaible/clst/{clst}")
    @ResponseBody
    private Integer getDeqPerreniteFaiblePerClst(@PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT deq.id) FROM article a, dossierequivalence deq, dossier_homologation dsh \n" +
                "                            WHERE dsh.pk_equivalence_id = deq.id\n" +
                "                       AND deq.id_art_init = a.id \n" +
                "                       AND deq.dsequiv_niveau_validation = 'En attente proposition'\n" +
                "                       AND a.art_couleur = 'vert'\n" +
                "                       AND a.art_followed_component = 'o' \n" +
                "\t\t\t\t\t             AND a.art_cycle_vie = 4   " +
                "                       AND dsh.pk_client_site_id = ? ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, clst);
        return count;
    }

    //list
    @GetMapping("/deqs/perrenitefaiblelist/clst/{clst}")
    @ResponseBody
    private List<DossierEquivalence> getDeqPerreniteFaibleListPerClst(@PathVariable Long clst) {
        String sql = "SELECT * FROM article a, dossierequivalence deq, dossier_homologation dsh \\n\" +\n" +
                "                    WHERE dsh.pk_equivalence_id = deq.id\\n\" +\n" +
                "                    AND deq.id_art_init = a.id \\n\" +\n" +
                "                    AND deq.dsequiv_niveau_validation = 'En attente proposition'\\n\" +\n" +
                "                    AND a.art_couleur = 'vert'\\n\" +\n" +
                "                    AND a.art_followed_component = 'o' \\n\" +\n" +
                "                    AND a.art_cycle_vie = 4   \" +\n" +
                "                    AND dsh.pk_client_site_id = ? ";
        List<DossierEquivalence> deqs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DossierEquivalence.class), clst);
        return deqs;
    }

    //maj par type de composant
    @GetMapping("/deqs/majpertype/{majFromDate}/type/{artType}/clst/{clst}")
    @ResponseBody
    private Integer getMajPerTypeCompPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable String artType, @PathVariable Long clst) {
        String sql = "SELECT COUNT(DISTINCT a.id) FROM article a, dossierequivalence deq, dossier_homologation dsh \n" +
                "                        WHERE dsh.pk_equivalence_id = deq.id\n" +
                "                        AND deq.id_art_init = a.id \n" +
                "                        AND a.art_couleur = 'vert' \n" +
                "                        AND a.art_followed_component = 'o' \n" +
                "                        AND a.art_type_composant = ? \n" +
                "                        AND a.art_date_couleur > ? \n" +
                "\t\t\t\t    \tAND dsh.pk_client_site_id = ?; ";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, artType, majFromDate, clst);
        return count;
    }

    //liste
    @GetMapping("/deqs/majpertypelist/{majFromDate}/type/{artType}/clst/{clst}")
    @ResponseBody
    private List<Article> getMajPerTypeCompListPerClst(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date majFromDate, @PathVariable String artType, @PathVariable Long clst) {
        String sql = "SELECT * FROM article a, dossierequivalence deq, dossier_homologation dsh \\n\" +\n" +
                "              WHERE dsh.pk_equivalence_id = deq.id\\n\" +\n" +
                "              AND deq.id_art_init = a.id \\n\" +\n" +
                "              AND a.art_couleur = 'vert' \\n\" +\n" +
                "              AND a.art_followed_component = 'o' \\n\" +\n" +
                "              AND a.art_type_composant = ? \\n\" +\n" +
                "              AND a.art_date_couleur > ? \\n\" +\n" +
                "              AND dsh.pk_client_site_id = ?; ";
        List<Article> articles = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class), artType, majFromDate, clst);
        return articles;
    }

}




