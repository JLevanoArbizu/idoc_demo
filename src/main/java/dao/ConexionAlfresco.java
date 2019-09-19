package dao;

//import java.util.HashMap;
//import java.util.Map;
//import org.apache.chemistry.opencmis.client.api.CmisObject;
//import org.apache.chemistry.opencmis.client.api.Document;
//import org.apache.chemistry.opencmis.client.api.Folder;
//import org.apache.chemistry.opencmis.client.api.ItemIterable;
//import org.apache.chemistry.opencmis.client.api.Session;
//import org.apache.chemistry.opencmis.client.api.SessionFactory;
//import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
//import org.apache.chemistry.opencmis.commons.SessionParameter;
//import org.apache.chemistry.opencmis.commons.enums.BindingType;
//import org.primefaces.model.StreamedContent;

public class ConexionAlfresco {

//    public static void main(String[] args) {
//
//        Map<String, String> sessionParameters = new HashMap<>();
//        sessionParameters.put(SessionParameter.USER, "admin");
//        sessionParameters.put(SessionParameter.PASSWORD, "Vallegrande2019");
//        sessionParameters.put(SessionParameter.ATOMPUB_URL, "http://35.207.57.5:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
//        sessionParameters.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
//
//        SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
//        Session lSession = sessionFactory.getRepositories(sessionParameters).get(0).createSession();
//
//        Folder root = lSession.getRootFolder();
//        ItemIterable<CmisObject> contentItems = root.getChildren();
//        for (CmisObject contentItem : contentItems) {
//            if (contentItem instanceof Document) {
//                Document docMetadata = (Document) contentItem;
//                StreamedContent sc = (StreamedContent) docMetadata.getContentStream();
//            } else {
//                System.out.println(contentItem.getName());
//            }
//        }
//    }
}
