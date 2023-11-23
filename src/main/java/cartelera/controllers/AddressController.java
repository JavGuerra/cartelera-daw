package cartelera.controllers;

import cartelera.entities.Address;
import cartelera.services.IAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static cartelera.utils.Utils.invalidPosNumber;

@AllArgsConstructor
@Controller
public class AddressController {

    private final IAddressService addressService;

    /**
     * Lista todas las direcciones.
     * @param model Modelo.
     * @return Plantilla addresses-list.
     */
    @GetMapping("/addresses")
    public String findAll(Model model) {
        List<Address> addresses = addressService.findAll();
        model.addAttribute("addresses", addresses);
        return "address/addresses-list";
    }

    /**
     * Muestra una dirección específica.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla address-detail.
     */
    @GetMapping("/address/{id}")
    public String findById(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && addressService.existsById(id))
            model.addAttribute("address", addressService.findById(id).get());
        else model.addAttribute("error", "Dirección no encontrada.");
        return "address/address-detail";
    }

    /**
     * Crea una nueva dirección.
     * @param model modelo.
     * @return Plantilla address-form.
     */
    @GetMapping("addresses/create")
    public String createForm(Model model) {
        model.addAttribute("address", new Address());
        return "address/address-form";
    }

    /**
     * Edita una dirección existente.
     * @param model Modelo.
     * @param id Identificador.
     * @return Plantilla address-form.
     */
    @GetMapping("addresses/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        if (!invalidPosNumber(id) && addressService.existsById(id))
            model.addAttribute("address", addressService.findById(id).get());
        else model.addAttribute("error", "Dirección no encontrada.");
        return "address/address-form";
    }

    /**
     * Guarda la dirección obtenida desde el formulario.
     * @param address Dirección.
     * @return Plantilla addresses.
     */
    @PostMapping("addresses")
    public String save(@ModelAttribute Address address) {
        addressService.save(address);
        return "redirect:/addresses";
    }

    /**
     * Borra una dirección por su ID.
     * @param id Identificador.
     * @return Plantilla addresses.
     */
    @GetMapping("addresses/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        if (!invalidPosNumber(id) && addressService.existsById(id)) addressService.deleteById(id);
        return "redirect:/addresses";
    }
}