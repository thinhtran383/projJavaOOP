package com.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class CreatePdf {

    public static void createPdf(AnchorPane anchorPane, String fileName) throws IOException {

        // Get the size of an A4 page in points (1 inch = 72 points)
        PDRectangle pageSize = PDRectangle.A4;

        // Create a new PDF document
        PDDocument document = new PDDocument();

        // Create a new page with A4 size
        PDPage page = new PDPage(pageSize);
        document.addPage(page);

        // Get the page width and height in points
        float pageWidth = pageSize.getWidth();
        float pageHeight = pageSize.getHeight();

        // Create a new content stream to write to the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Take a snapshot of the AnchorPane
        WritableImage snapshot = anchorPane.snapshot(new SnapshotParameters(), null);

        // Write the snapshot as a PNG file
        String pngFileName = fileName + ".png";
        File pngFile = new File(pngFileName);
        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", pngFile);

        // Load the PNG file into an image object
        byte[] imageBytes = Files.readAllBytes(Paths.get(pngFileName));
        PDImageXObject pdImage = LosslessFactory.createFromImage(document,
                ImageIO.read(new ByteArrayInputStream(imageBytes)));

        // Scale the image to fit the page width and height
        float scale = Math.min(pageWidth / pdImage.getWidth(), pageHeight / pdImage.getHeight());
        float imageWidth = pdImage.getWidth() * scale;
        float imageHeight = pdImage.getHeight() * scale;

        // Center the image on the page
        float x = (pageWidth - imageWidth) / 2f;
        float y = (pageHeight - imageHeight) / 2f;

        // Add the image to the content stream
        contentStream.drawImage(pdImage, x, y, imageWidth, imageHeight);

        // Close the content stream and save the PDF file
        contentStream.close();
        document.save(fileName);
        document.close();

        // Delete the PNG file
        pngFile.delete();
    }
}
