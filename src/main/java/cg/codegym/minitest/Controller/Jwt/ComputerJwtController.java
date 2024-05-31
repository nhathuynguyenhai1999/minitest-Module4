package cg.codegym.minitest.Controller.Jwt;

import cg.codegym.minitest.Model.Computer;
import cg.codegym.minitest.Model.dto.ComputerDTO;
import cg.codegym.minitest.Service.iml.ComputerService1;
import cg.codegym.minitest.jwt.JwtResponse;
import cg.codegym.minitest.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComputerJwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ComputerService1 computerService1;
    @Autowired
    private JwtService jwtService;

    /* ---------------- GET ALL COMPUTERS ------------------------ */
    @RequestMapping(value = "/computers1", method = RequestMethod.GET)
    public ResponseEntity<List<ComputerDTO>> getAllComputers() {
        return new ResponseEntity<>(computerService1.findAll(), HttpStatus.OK);
    }

    /* ---------------- GET COMPUTER BY ID ------------------------ */
    @RequestMapping(value = "/computers1/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        ComputerDTO computer = computerService1.findById(id);
        if (computer != null) {
            return new ResponseEntity<>(computer, HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found Computer", HttpStatus.NO_CONTENT);
    }

    /* ---------------- CREATE NEW COMPUTER ------------------------ */
    @RequestMapping(value = "/create-computers", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody Computer user) {
        if (computerService1.add(user)) {
            return new ResponseEntity<>("Created!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Computer Already Exists!", HttpStatus.BAD_REQUEST);
        }
    }

    /* ---------------- DELETE COMPUTER ------------------------ */
    @RequestMapping(value = "/computers1/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        computerService1.delete(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    /* ---------------- LOGIN ------------------------ */
    @PostMapping("/computers/login")
    public ResponseEntity<?> login(@RequestBody ComputerDTO computerDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(computerDTO.getName(), computerDTO.getCode()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Computer userInfo = computerService1.findByUsername(computerDTO.getName());
        return ResponseEntity.ok(new JwtResponse(userDetails.getAuthorities(), userInfo.getId(), userInfo.getCode(),
                jwt, userInfo.getName(), userInfo.getName()));
    }
}
