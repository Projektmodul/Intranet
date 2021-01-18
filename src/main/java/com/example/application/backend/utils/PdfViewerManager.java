package com.example.application.backend.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

import java.io.IOException;
import java.io.InputStream;

/*
 * Implemented functions:
 *  X - PDF is persistent on the page
 *  X - Successful upload means => save file in the database. This happens, only once the user confirms with a click on a
 *       save button.
 *    X   Then the page reloads, showing the pdf file in its place, and with the upload initialized
 *  X - Save button:
 *     X  - confirm saving the file on the server
 *     X  - this creates a DocumentEntity, with a path to the file on the server
 *     X  - this entity is then saved to the database with the service
 *     X  - the entity is then saved as the parent page's (ex. HelpView-Page) DocumentEntity
 * X  - The stream, byte[] etc. functions are in a separate class : PdfFileConverter.
 * X  - Download function already (unintentionally) implemented (see download button in the viewer)
 * X  - Save file in user repository ???
 *
 *   - Useful for the pages:
 *       - DocumentsView ???
 *       - CareerView
 *       - WelcomeView
 *       - CanteenView
 *       - SearchView
 *
 * */

@CssImport("./styles/views/main/content.css")
public class PdfViewerManager extends Div{


    private PdfFileConverter pdfFileConverter;


    private Upload upload;
    private MemoryBuffer memoryBuffer;

    private Div uploadOutput;

    private Component pdfFile;




    public PdfViewerManager(){

        this.pdfFileConverter = new PdfFileConverter();

        pdfFile = new PdfDocumentViewer();

        this.setId("pdfViewerContainer");
        initializeUploadOutputContainer();

    }

    public void initializeUpload(){
        memoryBuffer = new MemoryBuffer();
        upload = new Upload(memoryBuffer);
        getPdfFileFromUploader();
        upload.setId("uploadContainer");
        this.add(upload);
    }

    private void initializeUploadOutputContainer(){
        uploadOutput = new Div();
        uploadOutput.setId("uploadOutputContainer");

    }

    public void getPdfFileFromUploader(){

        upload.addSucceededListener(e -> {
            InputStream inputStream = memoryBuffer.getInputStream();
            pdfFile = createPdfFileFromUploader(e.getMIMEType(),
                    e.getFileName(), inputStream);
            showConfirmationButtons();
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

        //add the components to the view
        //need to make deliver/return separate components that could be added to the views.
    }

    /*mimeType is for the accepted types of files: .pdf
     * fileName read from the stream, upon upload
     * stream is what was uploaded
     *
     * if the file is a .pdf: return a component: pdfFileToView
     * converts the inputStream with the method getArrayFormInputStream
     * */
    private Component createPdfFileFromUploader(String mimeType, String fileName,
                                                InputStream stream){
        try{
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
        outputContainer.add(content);
    }

    public void showPagePdfs(){
        for(Component viewer : pdfFileConverter.createAllDocumentViewers()){
            //empty string should be changed
            Div pdfOutput = new Div();
            pdfOutput.setId("uploadOutputContainer");
            showOutput("PDF-Datei", viewer, pdfOutput);
            this.add(pdfOutput);
        }
    }

    private void showConfirmationButtons(){

        NativeButton cancelButton = new NativeButton("Nicht speichern");

        cancelButton.setClassName("uploadButtons");

        cancelButton.setEnabled(true);

        cancelButton.addClickListener( e-> {
            if(pdfFileConverter.deleteFile()) {
                cancelButton.setVisible(false);
                uploadOutput = new Div();
                initializeUpload();
                UI.getCurrent().getPage().reload();
            }
        });

        uploadOutput.add(cancelButton);

    }

    public PdfFileConverter getPdfFileConverter() {
        return pdfFileConverter;
    }

    public void setPdfFileConverter(PdfFileConverter pdfFileConverter) {
        this.pdfFileConverter = pdfFileConverter;
    }

//    FileSystemResource findInFileSystem(String location) {
//        try {
//            return new FileSystemResource(Paths.get(location));
//        } catch (Exception e) {
//            // Handle access or file not found problems.
//            throw new RuntimeException();
//        }
//    }
//This is for showing the pdf on the GUI, for example on the canteen view.
    //Path is from the database
//    public static String getPDF() throws IOException {
//
//        File file = new File("give complete path of file which must be read");
//        FileInputStream stream = new FileInputStream(file);
//        byte[] buffer = new byte[8192];
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        int bytesRead;enter code here
//        while ((bytesRead = stream.read(buffer)) != -1) {
//            baos.write(buffer, 0, bytesRead);
//        }
//        System.out.println("it came back"+baos);
//        byte[] buffer1= baos.toByteArray();
//        String fileName = "give your filename with location";
//
//        //stream.close();
//
//
//        FileOutputStream outputStream =
//                new FileOutputStream(fileName);
//
//        outputStream.write(buffer1);
//
//        return fileName;
//
//    }

    //If the file is an image, this could be helpful later
//    else if (mimeType.startsWith("image")) {
//        Image image = new Image();
//        try {
//
//            byte[] bytes = IOUtils.toByteArray(stream);
//            image.getElement().setAttribute("src", new StreamResource(
//                    fileName, () -> new ByteArrayInputStream(bytes)));
//            try (ImageInputStream in = ImageIO.createImageInputStream(
//                    new ByteArrayInputStream(bytes))) {
//                final Iterator<ImageReader> readers = ImageIO
//                        .getImageReaders(in);
//                if (readers.hasNext()) {
//                    ImageReader reader = readers.next();
//                    try {
//                        reader.setInput(in);
//                        image.setWidth(reader.getWidth(0) + "px");
//                        image.setHeight(reader.getHeight(0) + "px");
//                    } finally {
//                        reader.dispose();
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return image;
}
