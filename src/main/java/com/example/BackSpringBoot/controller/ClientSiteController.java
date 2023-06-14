package com.example.BackSpringBoot.controller;


import com.example.BackSpringBoot.exception.ResourceNotFoundException;
import com.example.BackSpringBoot.model.*;
import com.example.BackSpringBoot.repository.ClientRepository;
import com.example.BackSpringBoot.repository.ClientSiteRepository;
import com.example.BackSpringBoot.repository.SiteRepository;
import com.example.BackSpringBoot.service.ClientSiteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1/ipersyst/clientsite")
public class ClientSiteController {

    private final ClientSiteService clientSiteService;

    @Autowired
    private ClientSiteRepository clientSiteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ClientSiteController(ClientSiteService clientSiteService) {
        this.clientSiteService = clientSiteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientSite>> getAllClientSites(){
        List<ClientSite> clientSites = clientSiteService.findAll();
        return new ResponseEntity<>(clientSites, HttpStatus.OK) ;
    }

    @GetMapping("/client/{pkClient}")
    @ResponseBody
    public ResponseEntity<List<ClientSite>> getAllByClient(@PathVariable Long pkClient) {
        String sql = "SELECT * \n" +
                    "FROM public.client_site\n" +
                    "WHERE client_site.pk_client_id = ? ;";
        Object[] params = {pkClient};
        List<Map<String, Object>> clstsData = jdbcTemplate.queryForList(sql, params);
        List<ClientSite> clsts = new ArrayList<>();

        for (Map<String, Object> clstData : clstsData) {
            ClientSite clst = new ClientSite();
            clst.setId((Long) clstData.get("id"));
            clst.setTs((Timestamp) clstData.get("ts"));
            clst.setClstActif((boolean) clstData.get("clst_actif"));
            clst.setClsRapportSpec((boolean) clstData.get("cls_rapport_spec"));
            clst.setClstReference((String) clstData.get("clst_reference"));
            clst.setClstReferenceMovex((String) clstData.get("clst_reference_movex"));
            clst.setClstIb((String) clstData.get("clst_ib"));
            clst.setClstContact((String) clstData.get("clst_contact"));
            clst.setClstAdresse((String) clstData.get("clst_adresse"));
            clst.setClstCpville((String) clstData.get("clst_cpville"));
            clst.setClstWebSite((String) clstData.get("clst_web_site"));
            clst.setClstEmail((String) clstData.get("clst_email"));
            clst.setClstTel((String) clstData.get("clst_tel"));
            clst.setClstFax((String) clstData.get("clst_fax"));
            clst.setClstAdresseMovex((String) clstData.get("clst_adresse_movex"));
            clst.setClstLogo((String) clstData.get("clst_logo"));
            clst.setClstEmailDSH((String) clstData.get("clst_emaildsh"));
            //client
            Long pkClientId = (Long) clstData.get("pk_client_id");
            Client client = clientRepository.findById(pkClientId).orElse(null);
            clst.setPkClient(client);
            //site
            Long pkSiteId = (Long) clstData.get("pk_site_id");
            Site site = siteRepository.findById(pkSiteId).orElse(null);
            clst.setPkSite(site);
            clsts.add(clst);
        }

        ResponseEntity<List<ClientSite>> responseEntity = new ResponseEntity<>(clsts, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/site/{pkSite}")
    @ResponseBody
    public ResponseEntity<List<ClientSite>> getAllBySite(@PathVariable Long pkSite) {
        String sql = "SELECT * \n" +
                "FROM public.client_site\n" +
                "WHERE client_site.pk_site_id = ? ;";
        Object[] params = {pkSite};
        List<Map<String, Object>> clstsData = jdbcTemplate.queryForList(sql, params);
        List<ClientSite> clsts = new ArrayList<>();

        for (Map<String, Object> clstData : clstsData) {
            ClientSite clst = new ClientSite();
            clst.setId((Long) clstData.get("id"));
            clst.setTs((Timestamp) clstData.get("ts"));
            clst.setClstActif((boolean) clstData.get("clst_actif"));
            clst.setClsRapportSpec((boolean) clstData.get("cls_rapport_spec"));
            clst.setClstReference((String) clstData.get("clst_reference"));
            clst.setClstReferenceMovex((String) clstData.get("clst_reference_movex"));
            clst.setClstIb((String) clstData.get("clst_ib"));
            clst.setClstContact((String) clstData.get("clst_contact"));
            clst.setClstAdresse((String) clstData.get("clst_adresse"));
            clst.setClstCpville((String) clstData.get("clst_cpville"));
            clst.setClstWebSite((String) clstData.get("clst_web_site"));
            clst.setClstEmail((String) clstData.get("clst_email"));
            clst.setClstTel((String) clstData.get("clst_tel"));
            clst.setClstFax((String) clstData.get("clst_fax"));
            clst.setClstAdresseMovex((String) clstData.get("clst_adresse_movex"));
            clst.setClstLogo((String) clstData.get("clst_logo"));
            clst.setClstEmailDSH((String) clstData.get("clst_emaildsh"));
            //client
            Long pkClientId = (Long) clstData.get("pk_client_id");
            Client client = clientRepository.findById(pkClientId).orElse(null);
            clst.setPkClient(client);
            //site
            Long pkSiteId = (Long) clstData.get("pk_site_id");
            Site site = siteRepository.findById(pkSiteId).orElse(null);
            clst.setPkSite(site);
            clsts.add(clst);
        }

        ResponseEntity<List<ClientSite>> responseEntity = new ResponseEntity<>(clsts, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientSite> addSite(@RequestBody ClientSite clientSite){
        ClientSite newClientSite = clientSiteService.addClientSite(clientSite);
        return new ResponseEntity<>(newClientSite, HttpStatus.CREATED) ;
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ClientSite> updateSite(@PathVariable long id, @RequestBody ClientSite clientSite) {
        ClientSite existingClientSite = clientSiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client Site does not exist with id: " + id));

        // Copy only the provided properties while ignoring null or default values
        BeanUtils.copyProperties(clientSite, existingClientSite, getNullPropertyNames(clientSite));

        ClientSite updatedClientSite = clientSiteRepository.save(existingClientSite);
        return new ResponseEntity<>(updatedClientSite, HttpStatus.OK);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || pd.getName().equals("id")) { // Exclude 'id' property
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteClientSite(@PathVariable("id") long id){
        ClientSite deleteClientSite = clientSiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client site not exist with id: " + id));
        clientSiteRepository.delete(deleteClientSite);
        clientSiteService.deleteClientSite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
