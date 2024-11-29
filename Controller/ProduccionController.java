/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Database.DataBase;
import Model.Producción.Produccion;
import Model.Producción.ProduccionDTO;
import Model.Producción.ProduccionMapper;
import Model.Producción.ProducciónDAO;
import View.View;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.w3c.dom.*;  // Para trabajar con el DOM (Document Object Model)
import javax.xml.parsers.*;  // Para crear el documento XML (DocumentBuilderFactory, DocumentBuilder)
import java.io.*;  // Para trabajar con flujos de entrada/salida (File, FileOutputStream)
import java.util.List;  // Para usar List
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class ProduccionController {
    private ProducciónDAO dao;
    private View vista;
    private ProduccionMapper mapper;

    public ProduccionController(View vista) {
        this.vista = vista;
        mapper = new ProduccionMapper();
        try {
            dao=new ProducciónDAO(DataBase.getInstance().getConnection());
        } catch (IllegalStateException e) {
            vista.showError("Error: La conexión a la base de datos no está inicializada.");
            throw new RuntimeException(e);
        } catch (SQLException ex) {
            vista.showError("Error al conectar con la Base de Datos");
        }
    }
    
    public void insertar(Produccion produccion){
        if(produccion==null || !validateRequired(produccion)) {
            vista.showError("Faltan datos requeridos");
            return;
        }
        try {
            if (!validatePK(produccion.getId())){
                vista.showError("La cedula ingresada ya se encuentra registrada");
                return;
            }
            dao.create(mapper.toDto(produccion));
            vista.showMessage("Datos guardados correctamente");
        } catch (SQLException ex) {
            vista.showError("Ocurrio un error al guardar los datos: "+ ex.getMessage());
        }
    }
    
    public void read(Object id){
        try {
            ProduccionDTO produccionDTO = dao.read(id);
            if (produccionDTO != null) {
                vista.showMessage("Cultivo encontrado: " + produccionDTO);
            } else {
                vista.showError("Cultivo no encontrado con ID: " + id);
            }
        } catch (SQLException e) {
            vista.showError("Error al buscar el cultivo: " + e.getMessage());
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
     
    public boolean validateRequired(Produccion produccion) {
        return produccion.getId() != 0 &&
                produccion.getFecha() != null &&
                !produccion.getCalidad().trim().isEmpty()&&
                !produccion.getDestino().trim().isEmpty() &&
                produccion.getCantidad_Recolectada() != 0;
    }

    public boolean validatePK(int id) {
        try {
            return dao.validatePK(id);
        } catch (SQLException ex) {
            return false;
        }
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

    // Método auxiliar para agregar elementos al XML
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
