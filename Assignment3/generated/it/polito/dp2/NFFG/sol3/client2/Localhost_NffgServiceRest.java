
package it.polito.dp2.NFFG.sol3.client2;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.uri.UriTemplate;

@Generated(value = {
    "wadl|http://localhost:8080/NffgService/rest/application.wadl"
}, comments = "wadl2java, http://wadl.java.net", date = "2017-01-31T10:10:20.263+01:00")
public class Localhost_NffgServiceRest {

    /**
     * The base URI for the resource represented by this proxy
     * 
     */
    public final static URI BASE_URI;

    static {
        URI originalURI = URI.create("http://localhost:8080/NffgService/rest/");
        // Look up to see if we have any indirection in the local copy
        // of META-INF/java-rs-catalog.xml file, assuming it will be in the
        // oasis:name:tc:entity:xmlns:xml:catalog namespace or similar duck type
        java.io.InputStream is = Localhost_NffgServiceRest.class.getResourceAsStream("/META-INF/jax-rs-catalog.xml");
        if (is!=null) {
            try {
                // Ignore the namespace in the catalog, can't use wildcard until
                // we are sure we have XPath 2.0
                String found = javax.xml.xpath.XPathFactory.newInstance().newXPath().evaluate(
                    "/*[name(.) = 'catalog']/*[name(.) = 'uri' and @name ='" + originalURI +"']/@uri", 
                    new org.xml.sax.InputSource(is)); 
                if (found!=null && found.length()>0) {
                    originalURI = java.net.URI.create(found);
                }
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    is.close();
                } catch (java.io.IOException e) {
                }
            }
        }
        BASE_URI = originalURI;
    }

    public static Localhost_NffgServiceRest.Verifications verifications(Client client, URI baseURI) {
        return new Localhost_NffgServiceRest.Verifications(client, baseURI);
    }

    /**
     * Template method to allow tooling to customize the new Client
     * 
     */
    private static void customizeClientConfiguration(ClientConfig cc) {
    }

    /**
     * Template method to allow tooling to override Client factory
     * 
     */
    private static Client createClientInstance(ClientConfig cc) {
        return Client.create(cc);
    }

    /**
     * Create a new Client instance
     * 
     */
    public static Client createClient() {
        ClientConfig cc = new DefaultClientConfig();
        customizeClientConfiguration(cc);
        return createClientInstance(cc);
    }

    public static Localhost_NffgServiceRest.Verifications verifications() {
        return verifications(createClient(), BASE_URI);
    }

    public static Localhost_NffgServiceRest.Verifications verifications(Client client) {
        return verifications(client, BASE_URI);
    }

    public static Localhost_NffgServiceRest.Policies policies(Client client, URI baseURI) {
        return new Localhost_NffgServiceRest.Policies(client, baseURI);
    }

    public static Localhost_NffgServiceRest.Policies policies() {
        return policies(createClient(), BASE_URI);
    }

    public static Localhost_NffgServiceRest.Policies policies(Client client) {
        return policies(client, BASE_URI);
    }

    public static Localhost_NffgServiceRest.Nffgs nffgs(Client client, URI baseURI) {
        return new Localhost_NffgServiceRest.Nffgs(client, baseURI);
    }

    public static Localhost_NffgServiceRest.Nffgs nffgs() {
        return nffgs(createClient(), BASE_URI);
    }

    public static Localhost_NffgServiceRest.Nffgs nffgs(Client client) {
        return nffgs(client, BASE_URI);
    }

    public static class Nffgs {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;

        private Nffgs(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        /**
         * Create new instance using existing Client instance, and a base URI and any parameters
         * 
         */
        public Nffgs(Client client, URI baseUri) {
            _client = client;
            _uriBuilder = UriBuilder.fromUri(baseUri);
            _uriBuilder = _uriBuilder.path("/nffgs");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public it.polito.dp2.NFFG.sol3.client2.Nffgs getAsNffgsXml() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Nffgs.class);
        }

        public<T >T getAsXml(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsXml(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public it.polito.dp2.NFFG.sol3.client2.Nffgs getAsNffgsJson() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Nffgs.class);
        }

        public<T >T getAsJson(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsJson(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public<T >T postXml(Object input, GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            resourceBuilder = resourceBuilder.type("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T postXml(Object input, Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            resourceBuilder = resourceBuilder.type("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public<T >T postJson(Object input, GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            resourceBuilder = resourceBuilder.type("application/json");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T postJson(Object input, Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            resourceBuilder = resourceBuilder.type("application/json");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public Localhost_NffgServiceRest.Nffgs.Id id(String id) {
            return new Localhost_NffgServiceRest.Nffgs.Id(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), id);
        }

        public static class Id {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private Id(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public Id(Client client, URI baseUri, String id) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("{id}");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                _templateAndMatrixParameterValues.put("id", id);
            }

            /**
             * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
             * 
             */
            public Id(Client client, URI uri) {
                _client = client;
                StringBuilder template = new StringBuilder(BASE_URI.toString());
                if (template.charAt((template.length()- 1))!= '/') {
                    template.append("/nffgs/{id}");
                } else {
                    template.append("nffgs/{id}");
                }
                _uriBuilder = UriBuilder.fromPath(template.toString());
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                UriTemplate uriTemplate = new UriTemplate(template.toString());
                HashMap<String, String> parameters = new HashMap<String, String>();
                uriTemplate.match(uri.toString(), parameters);
                _templateAndMatrixParameterValues.putAll(parameters);
            }

            /**
             * Get id
             * 
             */
            public String getId() {
                return ((String) _templateAndMatrixParameterValues.get("id"));
            }

            /**
             * Duplicate state and set id
             * 
             */
            public Localhost_NffgServiceRest.Nffgs.Id setId(String id) {
                Map<String, Object> copyMap;
                copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                UriBuilder copyUriBuilder = _uriBuilder.clone();
                copyMap.put("id", id);
                return new Localhost_NffgServiceRest.Nffgs.Id(_client, copyUriBuilder, copyMap);
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Policies {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;

        private Policies(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        /**
         * Create new instance using existing Client instance, and a base URI and any parameters
         * 
         */
        public Policies(Client client, URI baseUri) {
            _client = client;
            _uriBuilder = UriBuilder.fromUri(baseUri);
            _uriBuilder = _uriBuilder.path("/policies");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public<T >T postXml(Object input, GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            resourceBuilder = resourceBuilder.type("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T postXml(Object input, Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            resourceBuilder = resourceBuilder.type("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public<T >T postJson(Object input, GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            resourceBuilder = resourceBuilder.type("application/json");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T postJson(Object input, Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            resourceBuilder = resourceBuilder.type("application/json");
            ClientResponse response;
            response = resourceBuilder.method("POST", ClientResponse.class, input);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public it.polito.dp2.NFFG.sol3.client2.Policies getAsPoliciesXml() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Policies.class);
        }

        public<T >T getAsXml(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsXml(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public it.polito.dp2.NFFG.sol3.client2.Policies getAsPoliciesJson() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Policies.class);
        }

        public<T >T getAsJson(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsJson(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public Localhost_NffgServiceRest.Policies.Id id(String id) {
            return new Localhost_NffgServiceRest.Policies.Id(_client, _uriBuilder.buildFromMap(_templateAndMatrixParameterValues), id);
        }

        public static class Id {

            private Client _client;
            private UriBuilder _uriBuilder;
            private Map<String, Object> _templateAndMatrixParameterValues;

            private Id(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
                _client = client;
                _uriBuilder = uriBuilder.clone();
                _templateAndMatrixParameterValues = map;
            }

            /**
             * Create new instance using existing Client instance, and a base URI and any parameters
             * 
             */
            public Id(Client client, URI baseUri, String id) {
                _client = client;
                _uriBuilder = UriBuilder.fromUri(baseUri);
                _uriBuilder = _uriBuilder.path("{id}");
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                _templateAndMatrixParameterValues.put("id", id);
            }

            /**
             * Create new instance using existing Client instance, and the URI from which the parameters will be extracted
             * 
             */
            public Id(Client client, URI uri) {
                _client = client;
                StringBuilder template = new StringBuilder(BASE_URI.toString());
                if (template.charAt((template.length()- 1))!= '/') {
                    template.append("/policies/{id}");
                } else {
                    template.append("policies/{id}");
                }
                _uriBuilder = UriBuilder.fromPath(template.toString());
                _templateAndMatrixParameterValues = new HashMap<String, Object>();
                UriTemplate uriTemplate = new UriTemplate(template.toString());
                HashMap<String, String> parameters = new HashMap<String, String>();
                uriTemplate.match(uri.toString(), parameters);
                _templateAndMatrixParameterValues.putAll(parameters);
            }

            /**
             * Get id
             * 
             */
            public String getId() {
                return ((String) _templateAndMatrixParameterValues.get("id"));
            }

            /**
             * Duplicate state and set id
             * 
             */
            public Localhost_NffgServiceRest.Policies.Id setId(String id) {
                Map<String, Object> copyMap;
                copyMap = new HashMap<String, Object>(_templateAndMatrixParameterValues);
                UriBuilder copyUriBuilder = _uriBuilder.clone();
                copyMap.put("id", id);
                return new Localhost_NffgServiceRest.Policies.Id(_client, copyUriBuilder, copyMap);
            }

            public<T >T deleteAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T deleteAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T deleteAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T deleteAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("DELETE", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T putXml(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                resourceBuilder = resourceBuilder.type("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T putXml(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                resourceBuilder = resourceBuilder.type("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T putJson(Object input, GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                resourceBuilder = resourceBuilder.type("application/json");
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class, input);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T putJson(Object input, Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                resourceBuilder = resourceBuilder.type("application/json");
                ClientResponse response;
                response = resourceBuilder.method("PUT", ClientResponse.class, input);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsXml(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsXml(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/xml");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

            public<T >T getAsJson(GenericType<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
                return response.getEntity(returnType);
            }

            public<T >T getAsJson(Class<T> returnType) {
                UriBuilder localUriBuilder = _uriBuilder.clone();
                WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
                com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
                resourceBuilder = resourceBuilder.accept("application/json");
                ClientResponse response;
                response = resourceBuilder.method("GET", ClientResponse.class);
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    if (response.getStatus()>= 400) {
                        throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                    }
                }
                if (!ClientResponse.class.isAssignableFrom(returnType)) {
                    return response.getEntity(returnType);
                } else {
                    return returnType.cast(response);
                }
            }

        }

    }

    public static class Verifications {

        private Client _client;
        private UriBuilder _uriBuilder;
        private Map<String, Object> _templateAndMatrixParameterValues;

        private Verifications(Client client, UriBuilder uriBuilder, Map<String, Object> map) {
            _client = client;
            _uriBuilder = uriBuilder.clone();
            _templateAndMatrixParameterValues = map;
        }

        /**
         * Create new instance using existing Client instance, and a base URI and any parameters
         * 
         */
        public Verifications(Client client, URI baseUri) {
            _client = client;
            _uriBuilder = UriBuilder.fromUri(baseUri);
            _uriBuilder = _uriBuilder.path("/verifications");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public it.polito.dp2.NFFG.sol3.client2.Policies getAsPoliciesXml() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Policies.class);
        }

        public<T >T getAsXml(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsXml(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public it.polito.dp2.NFFG.sol3.client2.Policies getAsPoliciesXml(String policyname) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            if (policyname == null) {
            }
            if (policyname!= null) {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", policyname);
            } else {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", ((Object[]) null));
            }
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Policies.class);
        }

        public<T >T getAsXml(String policyname, GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            if (policyname == null) {
            }
            if (policyname!= null) {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", policyname);
            } else {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", ((Object[]) null));
            }
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsXml(String policyname, Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            if (policyname == null) {
            }
            if (policyname!= null) {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", policyname);
            } else {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", ((Object[]) null));
            }
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/xml");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public it.polito.dp2.NFFG.sol3.client2.Policies getAsPoliciesJson() {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Policies.class);
        }

        public<T >T getAsJson(GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsJson(Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

        public it.polito.dp2.NFFG.sol3.client2.Policies getAsPoliciesJson(String policyname) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            if (policyname == null) {
            }
            if (policyname!= null) {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", policyname);
            } else {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", ((Object[]) null));
            }
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(it.polito.dp2.NFFG.sol3.client2.Policies.class);
        }

        public<T >T getAsJson(String policyname, GenericType<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            if (policyname == null) {
            }
            if (policyname!= null) {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", policyname);
            } else {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", ((Object[]) null));
            }
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (response.getStatus()>= 400) {
                throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
            }
            return response.getEntity(returnType);
        }

        public<T >T getAsJson(String policyname, Class<T> returnType) {
            UriBuilder localUriBuilder = _uriBuilder.clone();
            if (policyname == null) {
            }
            if (policyname!= null) {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", policyname);
            } else {
                localUriBuilder = localUriBuilder.replaceQueryParam("policyName", ((Object[]) null));
            }
            WebResource resource = _client.resource(localUriBuilder.buildFromMap(_templateAndMatrixParameterValues));
            com.sun.jersey.api.client.WebResource.Builder resourceBuilder = resource.getRequestBuilder();
            resourceBuilder = resourceBuilder.accept("application/json");
            ClientResponse response;
            response = resourceBuilder.method("GET", ClientResponse.class);
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                if (response.getStatus()>= 400) {
                    throw new Localhost_NffgServiceRest.WebApplicationExceptionMessage(Response.status(response.getClientResponseStatus()).build());
                }
            }
            if (!ClientResponse.class.isAssignableFrom(returnType)) {
                return response.getEntity(returnType);
            } else {
                return returnType.cast(response);
            }
        }

    }


    /**
     * Workaround for JAX_RS_SPEC-312
     * 
     */
    private static class WebApplicationExceptionMessage
        extends WebApplicationException
    {


        private WebApplicationExceptionMessage(Response response) {
            super(response);
        }

        /**
         * Workaround for JAX_RS_SPEC-312
         * 
         */
        public String getMessage() {
            Response response = getResponse();
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            if (status!= null) {
                return (response.getStatus()+(" "+ status.getReasonPhrase()));
            } else {
                return Integer.toString(response.getStatus());
            }
        }

        public String toString() {
            String s = "javax.ws.rs.WebApplicationException";
            String message = getLocalizedMessage();
            return (s +(": "+ message));
        }

    }

}
