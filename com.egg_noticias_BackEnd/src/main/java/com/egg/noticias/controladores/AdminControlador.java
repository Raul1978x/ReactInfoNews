package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticia;
import com.egg.noticias.entidades.Usuario;
//import com.egg.noticias.entidades.WeatherData;
import com.egg.noticias.excepciones.MiExcepcion;
import com.egg.noticias.servicios.BusinessService;
import com.egg.noticias.servicios.NoticiaServicio;
import com.egg.noticias.servicios.UsuarioServicio;
import com.egg.noticias.servicios.WeatherServicio;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminControlador {

    private static final Logger logger = LoggerFactory.getLogger(AdminControlador.class);

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/noticias")
    public ResponseEntity<List<Noticia>> listaNoticas() {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        ResponseEntity<List<Noticia>> responseEntity = ResponseEntity.ok(noticias);
        return responseEntity;
    }

    @GetMapping("/dashboard/{email}")
    public ResponseEntity<Usuario> admin(@PathVariable String email, HttpSession session) {
        Usuario usuario = usuarioServicio.buscarPorEmail(email);
//        if ("ADMIN".equals(usuario.getRol())) {
        ResponseEntity<Usuario> responseEntity = ResponseEntity.ok(usuario);
        return responseEntity;
//        }
//        ResponseEntity<Usuario> responseEntity = (ResponseEntity<Usuario>) ResponseEntity.notFound();
//        return responseEntity;
    }

    @GetMapping("/mostrarUsuario/{id}")
    public ResponseEntity<Usuario> mostrarUsuarioPorId(@PathVariable String id) {
        Usuario usuario = usuarioServicio.buscarUsuarioPorId(id);
        ResponseEntity<Usuario> responseEntity = ResponseEntity.ok(usuario);
        return responseEntity;
    }

    @GetMapping("/mostrarNoticia/{id}")
    public ResponseEntity<Noticia> mostrarNoticiaPorId(@PathVariable String id) {
        Noticia noticia = noticiaServicio.buscarNoticiaPorId(id);
        ResponseEntity<Noticia> responseEntity = ResponseEntity.ok(noticia);
        return responseEntity;
    }

    @PostMapping("/carga")
    public ResponseEntity<Noticia> cargarNoticia(@RequestParam MultipartFile archivo,
            @RequestParam String titulo, @RequestParam String cuerpo,
            @RequestParam String bajada)
            throws MiExcepcion {
        try {
            Noticia noticia = noticiaServicio.crearNoticia(archivo, titulo, cuerpo, bajada);
            ResponseEntity<Noticia> responseEntity = ResponseEntity.ok(noticia);
            return responseEntity;
        } catch (MiExcepcion e) {
            
            return null;
        }

    }
}
//    @GetMapping("/cargar")
//    public String noticia(ModelMap model) {
//        return "noticiaForm";
//    }
//
//   
//
//    @GetMapping("/editar/{id}")
//    public String editar(@PathVariable String id, ModelMap model) {
//        Noticia noticia = noticiaServicio.buscarNoticiaPorId(id);
//        model.put("noticia", noticia);
//        return "noticiaEditar";
//    }
//
//    @PostMapping("/edita/{id}")
//    public String editaNoticia(@RequestParam String id, @RequestParam String titulo,
//            @RequestParam String bajada, @RequestParam String cuerpo, ModelMap modelo,
//            MultipartFile archivo) throws MiExcepcion {
//        try {
//            noticiaServicio.actualizar(archivo, id, titulo, cuerpo, bajada);
//            modelo.put("exito", "la noticia se actualizo bien");
//        } catch (Exception e) {
//
//            modelo.put("error", e.getMessage());
//        }
//
//        return "redirect:/admin/dashboard";
//    }
//
//    @GetMapping("/eliminar/{id}")
//    public String eliminarPorId(@PathVariable String id, ModelMap model) {
////        getWeatherData(model);
//        List<Noticia> noticias = noticiaServicio.listarNoticias();
//        model.put("noticias", noticias);
//        noticiaServicio.eliminarPorId(id);
//        return "redirect:/admin/dashboard";
//    }
//
//   
//
//    @GetMapping("/usuarios")
//    public String mostrarUsuarios(ModelMap model) {
//        List<Usuario> usuarios = usuarioServicio.usuarios();
//        model.put("usuarios", usuarios);
//
//        return "listaUsuarios";
//    }
//
//    @GetMapping("/editarUsuario/{id}")
//    public String editarUsuario(@PathVariable String id, ModelMap model) throws MiExcepcion {
////        getWeatherData(model);
//        Usuario usuario = usuarioServicio.getOne(id);
//        model.put("usuario", usuario);
//        return "registro_editar";
//    }
//
//    @PostMapping("/editaUsuario/{id}")
//    public String editaUsuario(@RequestParam MultipartFile archivo, @PathVariable String id,
//            @RequestParam String nombre, @RequestParam String email,
//            @RequestParam String password, @RequestParam String password2,
//            ModelMap model, HttpSession session) throws MiExcepcion {
////        getWeatherData(model);
//        usuarioServicio.actualizar(archivo, id, nombre, email, password, password2);
//        model.put("exito", "el usuario se actualizo correctamente!!");
//        return "redirect:/admin/usuarios";
//    }
//
//    @GetMapping("/cambiarRol/{id}")
//    public String cambiarRol(@PathVariable String id) {
//
//        usuarioServicio.cambiarRol(id);
//
//        return "redirect:/admin/usuarios";
//
//    }
//
//    @GetMapping("/eliminarUsuario/{id}")
//    public String eliminarUsuario(@PathVariable String id) {
//
//        usuarioServicio.eliminarUsuario(id);
//
//        return "redirect:/admin/usuarios";
//
//    }
//
//    @GetMapping("/registrar")
//    public String registrar(ModelMap model) {
//        return "registro.html";
//    }
//
//    @PostMapping("/registro")
//    public String registro(@RequestParam MultipartFile archivo,
//            @RequestParam String nombre, @RequestParam String email,
//            @RequestParam String password, @RequestParam String password2,
//            ModelMap model) {
//        try {
//            usuarioServicio.registrar(archivo, nombre, email, password, password2);
//            model.put("exito", "Usuario Registrado");
//
//            return "redirect:/";
//
//        } catch (MiExcepcion ex) {
//            model.put("error", ex.getMessage());
//            model.put("nombre", nombre);
//            model.put("email", email);
//
//            return "registro";
//        }
//    }
//
////    Envio de Email
//    @Autowired
//    private BusinessService emailService;
//
//    @GetMapping("/enviarEmail/{id}")
//    public String sendEmail(@PathVariable String id, HttpSession session, ModelMap modelo) {
//        Usuario usuario = usuarioServicio.getOne(id);
//        modelo.put("usuario", usuario);
//        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
//        modelo.addAttribute("logueado", logueado.getNombre());
//        return "email";
//    }
//
//    @PostMapping("/envioEmail/{id}")
//    public String sendEmail2(@RequestParam(required = false) String email, @RequestParam String titulo, @RequestParam String textarea, @PathVariable(required = false) String id, ModelMap modelo) {
//        try {
//            emailService.sendEmail(email, titulo, textarea, id);
//            Usuario usuario = usuarioServicio.getOne(id);
//            modelo.put("usuario", usuario);
//            System.out.println(usuario.toString());
//            modelo.put("exito", "Mail enviado correctamente");
//            return "redirect:/admin/usuarios";
//        } catch (Exception ex) {
//            modelo.put("error", ex.getMessage());
//            return "redirect:/admin/enviarEmail/{id}";
//        }
//    }
//}
