/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Factory.FactoryProducer;
import Model.Cultivos.CultivosDTO;
import Model.DAO.DAO;
import Model.DAO.DAOFactory;
import Model.Mapper.Mapper;
import Model.Producción.Produccion;
import Model.Producción.ProduccionDTO;
import Model.Producción.ProducciónDAO;
import View.View;
import java.io.File;
import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Collectors;
import org.w3c.dom.*;  // Para trabajar con el DOM (Document Object Model)
import javax.xml.parsers.*;  // Para crear el documento XML (DocumentBuilderFactory, DocumentBuilder)   
import java.util.List; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class ProduccionController {
    private DAO<ProduccionDTO> dao;
    private View vista;
    private Mapper<Produccion, ProduccionDTO> mapper;
    private CultivosController cultivosController;
    
    public ProduccionController(View vista, CultivosController cultivosController) {
        this.vista = vista;
        this.cultivosController=cultivosController;
        try {
            DAOFactory factory = FactoryProducer.getFactory("Produccion"); // Indicamos el tipo de entidad
            this.dao = (DAO<ProduccionDTO>) factory.createDAO(DataBase.getInstance().getConnection());
            this.mapper = (Mapper<Produccion, ProduccionDTO>) factory.createrMapper();
        } catch (SQLException e) {
            vista.showError("Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
        public void insertar(Produccion produccion) {
        if (produccion == null || !validateRequired(produccion)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }
        try {
            CultivosDTO cultivoDTO = cultivosController.read(produccion.getIdCultivo(), false);
            if (cultivoDTO == null) {
                vista.showError("El cultivo con ID " + produccion.getIdCultivo() + " no existe. No se puede registrar la producción.");
                return;
            }
            if (!validatePK(produccion.getId())) {
                vista.showError("La producción con ID " + produccion.getId() + " ya está registrada.");
                return;
            }

            dao.create(mapper.toDto(produccion));
            vista.showMessage("Producción registrada con éxito.");
        } catch (SQLException ex) {
            vista.showError("Ocurrió un error al registrar la producción: " + ex.getMessage());
        }
    }
    
    public ProduccionDTO read(Object id,boolean showMessage){
        try {
            ProduccionDTO produccionDTO = dao.read(id);
            if (produccionDTO != null) {
                if(showMessage){
                    vista.showMessage("Produccion encontrado");
                }
                return produccionDTO;
            }else{
                if(showMessage){
                    vista.showError("Produccion no encontrado con el id " + id);
                }
                return null;
            }
        }catch(SQLException e){
            vista.showError("Error al buscar cultivo");
            return null;
        }
    }
    
    public void readAll(){
        try {
            List<ProduccionDTO> dtoList = dao.readAll();
            List<Produccion> produccionList = dtoList.stream()
                    .map(mapper::toEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            vista.showAll(produccionList);
        } catch (SQLException ex) {
            vista.showError("Error al cargar los datos: "+ ex.getMessage());
        }
    }
        public void update(Produccion produccion) {
        if (produccion == null || !validateRequired(produccion)) {
            vista.showError("Faltan datos requeridos.");
            return;
        }
        try {
            if (!validatePK(produccion.getId())) {
                vista.showError("La producción con ID " + produccion.getId() + " no existe.");
                return;
            }
           CultivosDTO cultivoDTO = cultivosController.read(produccion.getIdCultivo(), false);
            if (cultivoDTO == null) {
                vista.showError("El cultivo con ID " + produccion.getIdCultivo() + " no existe. No se puede actualizar la producción.");
                return;
            }
            dao.update(mapper.toDto(produccion));
            vista.showMessage("Producción actualizada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionController.class.getName()).log(Level.SEVERE, null, ex);
            vista.showError("Error al actualizar la producción: " + ex.getMessage());
        }
    }
    public void delete(int id) {
        try {
            if (!validatePK(id)) {
                vista.showError("La producción con ID " + id + " no existe.");
                return;
            }
            dao.delete(id);
            vista.showMessage("Producción eliminada con éxito.");
        } catch (SQLException ex) {
            Logger.getLogger(ProduccionController.class.getName()).log(Level.SEVERE, null, ex);
            vista.showError("Error al eliminar la producción: " + ex.getMessage());
        }
    }
     
    public boolean validateRequired(Produccion produccion) {
        return produccion.getId() != 0 &&
                produccion.getFecha() != null &&
                !produccion.getCalidad().trim().isEmpty()&&
                !produccion.getDestino().trim().isEmpty() &&
                !produccion.getCantidad_Recolectada().trim().isEmpty();
    }

    public boolean validatePK(int id) {
        try {
            return ((ProducciónDAO) dao).validatePK(id);
        } catch (SQLException ex) {
            Logger.getLogger(CultivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //cambiar metodo ahora en la noche
    public void generarReporteXML(List<ProduccionDTO> producciones) {
        try {
            // Crear un documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Crear el elemento raíz
            Element rootElement = document.createElement("Producciones");
            document.appendChild(rootElement);

            // Iterar sobre la lista de producciones y agregar elementos al XML
            for (ProduccionDTO produccion : producciones) {
                // Crear un elemento <Produccion>
                Element produccionElement = document.createElement("Produccion");
                rootElement.appendChild(produccionElement);

                // Crear y agregar los elementos hijo para cada atributo
                addElement(document, produccionElement, "ID", String.valueOf(produccion.getId()));
                addElement(document, produccionElement, "Fecha", produccion.getFecha().toString());
                addElement(document, produccionElement, "Calidad", produccion.getCalidad());
                addElement(document, produccionElement, "Destino", produccion.getDestino());
                addElement(document, produccionElement, "Cantidad_Recolectada", String.valueOf(produccion.getCantidad_Recolectada()));
            }

            // Escribir el documento XML a un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Crear el archivo de salida
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("reporte_produccion.xml"));

            // Escribir el archivo
            transformer.transform(source, result);
            vista.showMessage("Reporte XML generado correctamente.");

        } catch (Exception e) {
            vista.showError("Error al generar el reporte XML: " + e.getMessage());
        }
    }

    
    private void addElement(Document document, Element parent, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parent.appendChild(element);
    }
    public List<ProduccionDTO> getProduccionesFromDB() {
        try {
            return dao.readAll();  
        } catch (SQLException e) {
            vista.showError("Error al obtener producciones desde la base de datos: " + e.getMessage());
            return null;
        }
    }
}
