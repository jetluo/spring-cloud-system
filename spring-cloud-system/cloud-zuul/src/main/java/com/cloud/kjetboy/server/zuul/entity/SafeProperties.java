package com.cloud.kjetboy.server.zuul.entity;


import com.sgcc.uap.safe.service.Log;
import com.sgcc.uap.safe.service.Log4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import java.util.*;


@ConfigurationProperties(prefix = "cloud.safe")
public class SafeProperties {
    private final Log logger = (Log) new Log4j();

    private boolean enabled = false;

    private HttprequestonlyParams httprequestonly = new HttprequestonlyParams();

    private XframeoptionsParams xframeoptions = new XframeoptionsParams();

    private ResponseServerParams responseserver = new ResponseServerParams();

    private ErrorParams code4xx = new ErrorParams();

    private ErrorParams code5xx = new ErrorParams();

    private ErrorParams global = new ErrorParams();

    private FilterParams filter = new FilterParams();

    private HttpsParams https = new HttpsParams();

    private HeaderParams header = new HeaderParams();

    private CookieParams cookie = new CookieParams();

    private CrossParams cross = new CrossParams();

    private XssParams xss = new XssParams();

    private SqlInjectParams sqlinject = new SqlInjectParams();

    private KeywordParams keyword = new KeywordParams();

    private PpwParams ppw = new PpwParams();

    private UploadParams upload = new UploadParams();

    private DownloadParams download = new DownloadParams();

    private LogParams log = new LogParams();

    private AuditParams audit = new AuditParams();

    private TamperProofingParams tamperProofing = new TamperProofingParams();

    public HttprequestonlyParams getHttprequestonly() {
        return this.httprequestonly;
    }

    public void setHttprequestonly(HttprequestonlyParams httprequestonly) {
        this.httprequestonly = httprequestonly;
    }

    public XframeoptionsParams getXframeoptions() {
        return this.xframeoptions;
    }

    public void setXframeoptions(XframeoptionsParams xframeoptions) {
        this.xframeoptions = xframeoptions;
    }

    public ResponseServerParams getResponseserver() {
        return this.responseserver;
    }

    public void setResponseserver(ResponseServerParams responseserver) {
        this.responseserver = responseserver;
    }

    public ErrorParams getCode4xx() {
        return this.code4xx;
    }

    public void setCode4xx(ErrorParams code4xx) {
        this.code4xx = code4xx;
    }

    public ErrorParams getCode5xx() {
        return this.code5xx;
    }

    public void setCode5xx(ErrorParams code5xx) {
        this.code5xx = code5xx;
    }

    public ErrorParams getGlobal() {
        return this.global;
    }

    public void setGlobal(ErrorParams global) {
        this.global = global;
    }

    public HttpsParams getHttps() {
        return this.https;
    }

    public void setHttps(HttpsParams https) {
        this.https = https;
    }

    public HeaderParams getHeader() {
        return this.header;
    }

    public void setHeader(HeaderParams header) {
        this.header = header;
    }

    public CookieParams getCookie() {
        return this.cookie;
    }

    public void setCookie(CookieParams cookie) {
        this.cookie = cookie;
    }

    public XssParams getXss() {
        return this.xss;
    }

    public void setXss(XssParams xss) {
        this.xss = xss;
    }

    public SqlInjectParams getSqlinject() {
        return this.sqlinject;
    }

    public void setSqlinject(SqlInjectParams sqlinject) {
        this.sqlinject = sqlinject;
    }

    public KeywordParams getKeyword() {
        return this.keyword;
    }

    public void setKeyword(KeywordParams keyword) {
        this.keyword = keyword;
    }

    public UploadParams getUpload() {
        return this.upload;
    }

    public void setUpload(UploadParams upload) {
        this.upload = upload;
    }

    public DownloadParams getDownload() {
        return this.download;
    }

    public void setDownload(DownloadParams download) {
        this.download = download;
    }

    public CrossParams getCross() {
        return this.cross;
    }

    public void setCross(CrossParams cross) {
        this.cross = cross;
    }

    public PpwParams getPpw() {
        return this.ppw;
    }

    public void setPpw(PpwParams ppw) {
        this.ppw = ppw;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public FilterParams getFilter() {
        return this.filter;
    }

    public void setFilter(FilterParams filter) {
        this.filter = filter;
    }

    public LogParams getLog() {
        return this.log;
    }

    public void setLog(LogParams log) {
        this.log = log;
    }

    public AuditParams getAudit() {
        return this.audit;
    }

    public void setAudit(AuditParams audit) {
        this.audit = audit;
    }

    public TamperProofingParams getTamperProofing() {
        return this.tamperProofing;
    }

    public void setTamperProofing(TamperProofingParams tamperProofing) {
        this.tamperProofing = tamperProofing;
    }

    public Properties createProperties() {
        Properties properties = new Properties() {
            @Override
            public synchronized Object setProperty(String key, String value) {
                if (StringUtils.isBlank(value)) {
                    return null;
                }
                return super.setProperty(key, value);
            }
        };
        properties.setProperty("SAFE_HTTPREQUESTONLY_ENABLED", Boolean.toString(this.httprequestonly.isEnabled()));
        properties.setProperty("SAFE_XFRAMEOPTIONS_ENABLED", Boolean.toString(this.xframeoptions.isEnabled()));
        if (this.xframeoptions.getMode() != null) {
            properties.setProperty("SAFE_XFRAMEOPTIONS_MODE", this.xframeoptions.getMode());
        }
        properties.setProperty("SAFE_RESPONSE_SERVER_ENABLED", Boolean.toString(this.responseserver.isEnabled()));
        if (this.responseserver.getName() != null) {
            properties.setProperty("SAFE_RESPONSE_SERVER_NAME", this.responseserver.getName());
        }
        properties.setProperty("SAFE_CODE4XX_ENABLED", Boolean.toString(this.code4xx.isEnabled()));
        if (this.code4xx.getError() != null) {
            properties.setProperty("SAFE_CODE4XX_ERROR_CODEPRINT",
                    Boolean.toString(this.code4xx.getError().isCodepoint()));
            properties.setProperty("SAFE_CODE4XX_ERROR_PAGE", this.code4xx.getError().getPage());
        }
        properties.setProperty("SAFE_CODE5XX_ENABLED", Boolean.toString(this.code5xx.isEnabled()));
        if (this.code5xx.getError() != null) {
            properties.setProperty("SAFE_CODE5XX_ERROR_CODEPRINT",
                    Boolean.toString(this.code5xx.getError().isCodepoint()));
            properties.setProperty("SAFE_CODE5XX_ERROR_PAGE", this.code5xx.getError().getPage());
        }
        if (this.global.getError() != null) {
            properties.setProperty("SAFE_GLOBAL_ERROR_CODEPRINT",
                    Boolean.toString(this.global.getError().isCodepoint()));
            properties.setProperty("SAFE_GLOBAL_ERROR_PAGE", this.global.getError().getPage());
        }
        properties.setProperty("SAFE_HTTPS_ENABLED", Boolean.toString(this.https.isEnabled()));
        properties.setProperty("SAFE_HEADER_ENABLED", Boolean.toString(this.header.isEnabled()));
        properties.setProperty("SAFE_COOKIE_ENABLED", Boolean.toString(this.cookie.isEnabled()));
        properties.setProperty("SAFE_CROSS_ENABLED", Boolean.toString(this.cross.isEnabled()));
        properties.setProperty("SAFE_XSS_ENABLED", Boolean.toString(this.xss.isEnabled()));
        properties.setProperty("SAFE_SQLINJECT_ENABLED", Boolean.toString(this.sqlinject.isEnabled()));
        properties.setProperty("SAFE_KEYWORD_ENABLED", Boolean.toString(this.keyword.isEnabled()));
        properties.setProperty("SAFE_PPW_ENABLED", Boolean.toString(this.ppw.isEnabled()));
        properties.setProperty("SAFE_UPLOAD_ENABLED", Boolean.toString(this.upload.isEnabled()));
        properties.setProperty("SAFE_DOWNLOAD_ENABLED", Boolean.toString(this.download.isEnabled()));
        if (this.https.getOrder() != null) {
            properties.setProperty("SAFE_HTTPS_ORDER", Integer.toString(this.https.getOrder().intValue()));
        }
        if (this.header.getOrder() != null) {
            properties.setProperty("SAFE_HEADER_ORDER", Integer.toString(this.header.getOrder().intValue()));
        }
        if (this.cookie.getOrder() != null) {
            properties.setProperty("SAFE_COOKIE_ORDER", Integer.toString(this.cookie.getOrder().intValue()));
        }
        if (this.cross.getOrder() != null) {
            properties.setProperty("SAFE_CROSS_ORDER", Integer.toString(this.cross.getOrder().intValue()));
        }
        if (this.xss.getOrder() != null) {
            properties.setProperty("SAFE_XSS_ORDER", Integer.toString(this.xss.getOrder().intValue()));
        }
        if (this.sqlinject.getOrder() != null) {
            properties.setProperty("SAFE_SQLINJECT_ORDER", Integer.toString(this.sqlinject.getOrder().intValue()));
        }
        if (this.keyword.getOrder() != null) {
            properties.setProperty("SAFE_KEYWORD_ORDER", Integer.toString(this.keyword.getOrder().intValue()));
        }
        if (this.ppw.getOrder() != null) {
            properties.setProperty("SAFE_PPW_ORDER", Integer.toString(this.ppw.getOrder().intValue()));
        }
        if (this.upload.getOrder() != null) {
            properties.setProperty("SAFE_UPLOAD_ORDER", Integer.toString(this.upload.getOrder().intValue()));
        }
        if (this.download.getOrder() != null) {
            properties.setProperty("SAFE_DOWNLOAD_ORDER", Integer.toString(this.download.getOrder().intValue()));
        }
        if (this.header.getHost() != null) {
            properties.setProperty("SAFE_HEADER_HOST_ALLOWED", this.header.getHost().getAllowed());
        }
        if (this.header.getMethod() != null) {
            properties.setProperty("SAFE_HEADER_METHOD_ALLOWED", this.header.getMethod().getAllowed());
        }
        properties.setProperty("SAFE_COOKIE_KEYWORDS", this.cookie.getKeywords());
        if (this.cross.getAllowed() != null) {
            properties.setProperty("SAFE_CROSS_ALLOWED_CONTEXT",
                    listToCommaDelimitedString(this.cross.getAllowed().getContext()));
            properties.setProperty("SAFE_CROSS_ALLOWED_REFERER",
                    listToCommaDelimitedString(this.cross.getAllowed().getReferer()));
            addListParams("SAFE_CROSS_ALLOWED_WHITERAGEX_", this.cross.allowed.whiteragex, properties);
        }
        if (this.xss.getInputstream() != null) {
            properties.setProperty("SAFE_XSS_INPUTSTREAM_ENABLED",
                    Boolean.toString(this.xss.getInputstream().isEnabled()));
        }
        if (this.xss.getMultipart() != null) {
            properties.setProperty("SAFE_XSS_MULTIPART_ENABLED", Boolean.toString(this.xss.getMultipart().isEnabled()));
        }
        addListParams("SAFE_XSS_RAGEX_", this.xss.getRagex(), properties);
        addMapParams("SAFE_XSS_RAGEX_URI", this.xss.getUri(), properties);
        if (this.sqlinject.getInputstream() != null) {
            properties.setProperty("SAFE_SQLINJECT_INPUTSTREAM_ENABLED",
                    Boolean.toString(this.sqlinject.getInputstream().isEnabled()));
        }
        if (this.sqlinject.getMultipart() != null) {
            properties.setProperty("SAFE_SQLINJECT_MULTIPART_ENABLED",
                    Boolean.toString(this.sqlinject.getMultipart().isEnabled()));
        }
        if (this.sqlinject.getLevel() != null) {
            properties.setProperty("SAFE_SQLINJECT_LEVEL", this.sqlinject.getLevel().name());
        }
        properties.setProperty("SAFE_SQLINJECT_SELFDEFINE", this.sqlinject.getSelfdefine());
        properties.setProperty("SAFE_SQLINJECT_ALLOWSINGLEQUOTES",
                Boolean.toString(this.sqlinject.isAllowsinglequotes()));
        if (this.keyword.getInputstream() != null) {
            properties.setProperty("SAFE_KEYWORD_INPUTSTREAM_ENABLED",
                    Boolean.toString(this.keyword.getInputstream().isEnabled()));
        }
        if (this.keyword.getMultipart() != null) {
            properties.setProperty("SAFE_KEYWORD_MULTIPART_ENABLED",
                    Boolean.toString(this.keyword.getMultipart().isEnabled()));
        }
        addListParams("SAFE_KEYWORD_REPLAC_", this.keyword.getReplac(), properties);
        properties.setProperty("SAFE_PPW_PWSTR", this.ppw.getPwStr());
        properties.setProperty("SAFE_UPLOAD_BLACKLIST", this.upload.getBlacklist());
        properties.setProperty("SAFE_UPLOAD_WHITELIST", this.upload.getWhitelist());
        if (this.upload.getMaxsize() != null) {
            properties.setProperty("SAFE_UPLOAD_MAXSIZE", this.upload.getMaxsize());
        }
        addMapParams("SAFE_UPLOAD_WHITELIST_URI", this.upload.getUri(), properties);
        properties.setProperty("SAFE_DOWNLOAD_URL", listToCommaDelimitedString(this.download.getUrls()));
        properties.setProperty("SAFE_DOWNLOAD_FILENAMEPARAM", this.download.getFilenameparam());
        properties.setProperty("SAFE_LOG_SERVICE", this.log.getService());
        properties.setProperty("SAFE_AUDIT_SERVICE", this.audit.getService());
        this.logger.debug("Safe Boot Properties : ", new Object[0]);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            this.logger.debug((new StringBuilder())
                    .append(entry.getKey())
                    .append("=")
                    .append(StringEscapeUtils.escapeJava(entry.getValue().toString())).toString(), new Object[0]);
        }
        return properties;
    }

    private void addListParams(String prefix, List<String> list, Properties properties) {
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                properties.setProperty(prefix + (i + 1), list.get(i));
            }
        }
    }

    private void addMapParams(String prefix, Map<String, List<String>> map, Properties properties) {
        int n = 0;
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                List<String> values = entry.getValue();
                if (values == null || values.isEmpty()) {
                    continue;
                }
                if (StringUtils.isEmpty(values.get(0))) {
                    continue;
                }
                n++;
                int index = -1;
                for (String value : values) {
                    if (StringUtils.isEmpty(value)) {
                        continue;
                    }
                    index++;
                    properties.setProperty(prefix + n + "_" + index, value.trim());
                }
            }
        }
    }

    private String listToCommaDelimitedString(List<String> list) {
        if (!CollectionUtils.isEmpty(list)) {
            return org.springframework.util.StringUtils.arrayToCommaDelimitedString(list.toArray());
        }
        return null;
    }

    private enum SqlLevel {
        HIGH, MEDIUM, LOWER;
    }

    public static class FilterParams {
        private Integer order = Integer.valueOf(-2147483648);

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }
    }

    public static class HttprequestonlyParams {
        private boolean enabled = false;

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class ErrorParams {
        private boolean enabled = false;

        private SafeProperties.Error error;

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public SafeProperties.Error getError() {
            return this.error;
        }

        public void setError(SafeProperties.Error error) {
            this.error = error;
        }
    }

    public static class Error {
        private String page;

        private boolean codepoint;

        public String getPage() {
            return this.page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public boolean isCodepoint() {
            return this.codepoint;
        }

        public void setCodepoint(boolean codepoint) {
            this.codepoint = codepoint;
        }
    }

    public static class HttpsParams {
        private boolean enabled = false;

        private Integer order;

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class HeaderParams {
        private boolean enabled = false;

        private Integer order;

        private Host host;

        private Method method;

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Host getHost() {
            return this.host;
        }

        public void setHost(Host host) {
            this.host = host;
        }

        public Method getMethod() {
            return this.method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public static class Host {
            private String allowed;

            public String getAllowed() {
                return this.allowed;
            }

            public void setAllowed(String allowed) {
                this.allowed = allowed;
            }
        }

        public static class Method {
            private String allowed;

            public String getAllowed() {
                return this.allowed;
            }

            public void setAllowed(String allowed) {
                this.allowed = allowed;
            }
        }
    }

    public static class Host {
        private String allowed;

        public String getAllowed() {
            return this.allowed;
        }

        public void setAllowed(String allowed) {
            this.allowed = allowed;
        }
    }

    public static class Method {
        private String allowed;

        public String getAllowed() {
            return this.allowed;
        }

        public void setAllowed(String allowed) {
            this.allowed = allowed;
        }
    }

    public static class CookieParams {
        private boolean enabled = false;

        private Integer order;

        private String keywords;

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getKeywords() {
            return this.keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }
    }

    public static class CrossParams {
        private boolean enabled = false;

        private Integer order;

        private Allowed allowed = new Allowed();

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Allowed getAllowed() {
            return this.allowed;
        }

        public void setAllowed(Allowed allowed) {
            this.allowed = allowed;
        }

        public static class Allowed {
            private List<String> context = new ArrayList<>();

            private List<String> referer = new ArrayList<>();

            private List<String> whiteragex = new ArrayList<>();

            public List<String> getContext() {
                return this.context;
            }

            public void setContext(List<String> context) {
                this.context = context;
            }

            public List<String> getReferer() {
                return this.referer;
            }

            public void setReferer(List<String> referer) {
                this.referer = referer;
            }

            public List<String> getWhiteragex() {
                return this.whiteragex;
            }

            public void setWhiteragex(List<String> whiteragex) {
                this.whiteragex = whiteragex;
            }
        }
    }

    public static class Allowed {
        private List<String> context;

        private List<String> referer;

        private List<String> whiteragex;

        public Allowed() {
            this.context = new ArrayList<>();
            this.referer = new ArrayList<>();
            this.whiteragex = new ArrayList<>();
        }

        public List<String> getContext() {
            return this.context;
        }

        public void setContext(List<String> context) {
            this.context = context;
        }

        public List<String> getReferer() {
            return this.referer;
        }

        public void setReferer(List<String> referer) {
            this.referer = referer;
        }

        public List<String> getWhiteragex() {
            return this.whiteragex;
        }

        public void setWhiteragex(List<String> whiteragex) {
            this.whiteragex = whiteragex;
        }
    }

    public static class XssParams {
        private boolean enabled = false;

        private Integer order;

        private Inputstream inputstream;

        private Multipart multipart;

        private List<String> ragex = new ArrayList<>();

        private Map<String, List<String>> uri = new HashMap<>();

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Inputstream getInputstream() {
            return this.inputstream;
        }

        public void setInputstream(Inputstream inputstream) {
            this.inputstream = inputstream;
        }

        public Multipart getMultipart() {
            return this.multipart;
        }

        public void setMultipart(Multipart multipart) {
            this.multipart = multipart;
        }

        public List<String> getRagex() {
            return this.ragex;
        }

        public void setRagex(List<String> ragex) {
            this.ragex = ragex;
        }

        public Map<String, List<String>> getUri() {
            return this.uri;
        }

        public void setUri(Map<String, List<String>> uri) {
            this.uri = uri;
        }

        public static class Inputstream {
            private boolean enabled = false;

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Multipart {
            private boolean enabled = false;

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    public static class Inputstream {
        private boolean enabled;

        public Inputstream() {
            this.enabled = false;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class Multipart {
        private boolean enabled;

        public Multipart() {
            this.enabled = false;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    public static class SqlInjectParams {
        private boolean enabled = false;

        private Integer order;

        private Inputstream inputstream;

        private Multipart multipart;

        private SqlLevel level = SqlLevel.MEDIUM;

        private String selfdefine;

        private boolean allowsinglequotes = false;

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Inputstream getInputstream() {
            return this.inputstream;
        }

        public void setInputstream(Inputstream inputstream) {
            this.inputstream = inputstream;
        }

        public Multipart getMultipart() {
            return this.multipart;
        }

        public void setMultipart(Multipart multipart) {
            this.multipart = multipart;
        }

        public SqlLevel getLevel() {
            return this.level;
        }

        public void setLevel(SqlLevel level) {
            this.level = level;
        }

        public String getSelfdefine() {
            return this.selfdefine;
        }

        public void setSelfdefine(String selfdefine) {
            this.selfdefine = selfdefine;
        }

        public boolean isAllowsinglequotes() {
            return this.allowsinglequotes;
        }

        public void setAllowsinglequotes(boolean allowsinglequotes) {
            this.allowsinglequotes = allowsinglequotes;
        }

        private enum SqlLevel {
            HIGH, MEDIUM, LOWER;
        }

        public static class Inputstream {
            private boolean enabled = false;

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Multipart {
            private boolean enabled = false;

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }

    public static class KeywordParams {
        private boolean enabled = false;

        private Integer order;

        private Inputstream inputstream;

        private Multipart multipart;

        private List<String> replac = new ArrayList<>();

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Inputstream getInputstream() {
            return this.inputstream;
        }

        public void setInputstream(Inputstream inputstream) {
            this.inputstream = inputstream;
        }

        public Multipart getMultipart() {
            return this.multipart;
        }

        public void setMultipart(Multipart multipart) {
            this.multipart = multipart;
        }

        public List<String> getReplac() {
            return this.replac;
        }

        public void setReplac(List<String> replac) {
            this.replac = replac;
        }

        public static class Inputstream {
            private boolean enabled = false;

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }

        public static class Multipart {
            private boolean enabled = false;

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }
        }
    }


    public static class PpwParams {
        private boolean enabled = false;

        private Integer order;

        private String pwStr;

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getPwStr() {
            return this.pwStr;
        }

        public void setPwStr(String pwStr) {
            this.pwStr = pwStr;
        }
    }

    public static class UploadParams {
        private boolean enabled = false;

        private Integer order;

        private String blacklist;

        private String whitelist;

        private String maxsize = "4096";

        private Map<String, List<String>> uri = new HashMap<>();

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getBlacklist() {
            return this.blacklist;
        }

        public void setBlacklist(String blacklist) {
            this.blacklist = blacklist;
        }

        public String getWhitelist() {
            return this.whitelist;
        }

        public void setWhitelist(String whitelist) {
            this.whitelist = whitelist;
        }

        public String getMaxsize() {
            return this.maxsize;
        }

        public void setMaxsize(String maxsize) {
            this.maxsize = maxsize;
        }

        public Map<String, List<String>> getUri() {
            return this.uri;
        }

        public void setUri(Map<String, List<String>> uri) {
            this.uri = uri;
        }
    }

    public static class DownloadParams {
        private boolean enabled = false;

        private Integer order;

        private List<String> urls = new ArrayList<>();

        private String filenameparam;

        public Integer getOrder() {
            return this.order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<String> getUrls() {
            return this.urls;
        }

        public void setUrls(List<String> urls) {
            this.urls = urls;
        }

        public String getFilenameparam() {
            return this.filenameparam;
        }

        public void setFilenameparam(String filenameparam) {
            this.filenameparam = filenameparam;
        }
    }

    public static class XframeoptionsParams {
        private boolean enabled = false;

        private String mode = "sameorigin";

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getMode() {
            return this.mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }
    }

    public static class ResponseServerParams {
        private boolean enabled = false;

        private String name = "Unknown";

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class LogParams {
        private String service;

        public String getService() {
            return this.service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }

    public static class AuditParams {
        private String service;

        public String getService() {
            return this.service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }

    public static class TamperProofingParams {
        private boolean enabled = false;

        private List<String> ignored = new ArrayList<>();

        public boolean isEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public List<String> getIgnored() {
            return this.ignored;
        }

        public void setIgnored(List<String> ignored) {
            this.ignored = ignored;
        }
    }
}