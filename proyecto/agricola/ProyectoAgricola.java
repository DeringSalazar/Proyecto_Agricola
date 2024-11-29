/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.agricola;

import java.io.File;
import java.sql.SQLException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
public class ProyectoAgricola {

    public static void main(String[] args) throws SQLException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            // Crear un flujo de contenido en la página
            File fontFile = new File("C:/Windows/Fonts/arial.ttf");
            PDFont font = PDType0Font.load(document, fontFile);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(font, 12);

            // Escribir un título en el PDF
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Posición inicial
            contentStream.showText("Reporte de Prueba - Producción Agrícola");
            contentStream.endText();

            // Escribir datos simulados en el PDF
            String[] datosSimulados = {
                "ID: 1, Fecha: 2024-11-01, Calidad: Alta, Destino: Venta, Cantidad Recolectada: 200",
                "ID: 2, Fecha: 2024-11-02, Calidad: Media, Destino: Almacenamiento, Cantidad Recolectada: 150",
                "ID: 3, Fecha: 2024-11-03, Calidad: Baja, Destino: Venta, Cantidad Recolectada: 100"
            };

            int yPosition = 730; // Posición inicial para los datos
            for (String dato : datosSimulados) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition); // Posicionar el texto
                contentStream.showText(dato);
                contentStream.endText();
                yPosition -= 20; // Mover hacia abajo para la siguiente línea
            }

            // Cerrar el flujo de contenido
            contentStream.close();

            // Guardar el archivo PDF
            document.save("reporte_prueba.pdf");
            System.out.println("Reporte generado exitosamente: reporte_prueba.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Cerrar el documento PDF
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    }
}
