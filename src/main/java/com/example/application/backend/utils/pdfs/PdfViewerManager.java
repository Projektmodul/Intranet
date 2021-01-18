package com.example.application.backend.utils.pdfs;

import com.example.application.backend.entities.DocumentEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Implemented functions:
 *  *  X - PDF is persistent on the page
 *  *  X - Successful upload means => save file in the database. This happens, only once the user confirms with a click on a
 *  *       save button.
 *  *    X   Then the page reloads, showing the pdf file in its place, and with the upload initialized
 *  *  X - Save button:
 *  *     X  - confirm saving the file on the server
 *  *     X  - this creates a DocumentEntity, with a path to the file on the server
 *  *     X  - this entity is then saved to the database with the service
 *  *     X  - the entity is then saved as the parent page's (ex. HelpView-Page) DocumentEntity
 *  * X  - The stream, byte[] etc. functions are in a separate class : PdfFileConverter.
 *  * X  - Download function already (unintentionally) implemented (see download button in the viewer)
 *  * X  - Save file in user repository ???
 *  *
 *  *   - Useful for the pages:
 *  *       - DocumentsView ???
 *  *       - CareerView
 *  *       - WelcomeView
 *  *       - CanteenView
 *  *       - SearchView
 *
 * @author  Sabrine Gamdou
 * @version 1.0
 * @since   18.01.2021
 * @lastUpdated  18.01.2021
 */


@CssImport("./styles/views/main/content.css")
public class PdfViewerManager extends Div {


    private PdfFileConverter pdfFileConverter;


    private Upload upload;
    private MemoryBuffer memoryBuffer;

    private Div uploadOutput;

    private Component pdfFile;




    public PdfViewerManager() {

        this.pdfFileConverter = new PdfFileConverter();

        pdfFile = new PdfDocumentViewer();

        this.setId("pdfViewerContainer");

        initializeUploadOutputContainer();

    }

    public void initializeUpload() {
        memoryBuffer = new MemoryBuffer();
        upload = new Upload(memoryBuffer);
        getPdfFileFromUploader();
        upload.setId("uploadContainer");
        this.add(upload);
    }

    private void initializeUploadOutputContainer() {
        uploadOutput = new Div();
        uploadOutput.setId("uploadOutputContainer");

    }

    public void getPdfFileFromUploader() {

        upload.addSucceededListener(e -> {
            InputStream inputStream = memoryBuffer.getInputStream();
            pdfFile = createPdfFileFromUploader(e.getMIMEType(),
                    e.getFileName(), inputStream);
            this.add(uploadOutput);
            showOutput(e.getFileName(), pdfFile, uploadOutput);
            uploadOutput.setVisible(true);
        });

        upload.addFileRejectedListener(e -> {
            Paragraph errorMessage = new Paragraph();
            showOutput(e.getErrorMessage(), errorMessage, uploadOutput);
        });

        upload.addFailedListener(e -> {
            Paragraph failureMessage = new Paragraph();
            showOutput("Failed to upload the file", failureMessage, uploadOutput);
        });


    }

    /*mimeType is for the accepted types of files: .pdf
     * fileName read from the stream, upon upload
     * stream is what was uploaded
     *
     * if the file is a .pdf: return a component: pdfFileToView
     * converts the inputStream with the method getArrayFormInputStream
     * */
    private Component createPdfFileFromUploader(String mimeType, String fileName,
                                                InputStream stream) {
        try {
            if (mimeType.startsWith("application")) {

                return pdfFileConverter.createFileFromStream(stream,
                        pdfFileConverter.getArrayFromInputStream(stream), fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Div content = new Div();
        content.setId("pdfViewer");

        return content;
    }

    private void showOutput(String text, Component content,
                            HasComponents outputContainer) {
        H4 filenameLabel = new H4(text);

        outputContainer.add(filenameLabel);
        outputContainer.add(pdfFileConverter.setDeleteButton());
        outputContainer.add(content);
    }

    public PdfFileConverter getPdfFileConverter() {
        return pdfFileConverter;
    }

    public void setPdfFileConverter(PdfFileConverter pdfFileConverter) {
        this.pdfFileConverter = pdfFileConverter;
    }

}
