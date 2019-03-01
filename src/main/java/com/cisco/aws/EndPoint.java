package com.cisco.aws;

import sun.print.resources.serviceui_it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

/**
 * Container representing an Endpoint and its various attributes.
 */
public class EndPoint {

    /**
     * number of milisec in a day.
     */
    private static final int MILLIS_PER_DAY = (1000 * 60 * 60 * 24);

    /**
     * List of IPAddress attributes.
     */
    private static final String[] ipAttrs = {"Framed-IP-Address", "dhcp-requested-address", "cdpCacheAddress", "assetIpAddress"};
    /**
     * List of operating system attributes.
     * <p>
     * In order:
     * <ol>
     * <li>Active Directory</li>
     * <li>SMB</li>
     * <li>NMAP</li>
     * <li>HTTP user-agent</li>
     * </ol>
     */
    public static final String[] OS_ATTRS = {"AD-Operating-System", "SMB.operating-system", "operating-system", "OperatingSystem", "PostureOS"};
    /**
     * EndPoint ip attribute name.
     */
    public static final String ENDPOINTIP = "ip";
    /**
     * EndPoint ipv6 attribute name. Contains a list of ipv6 comma separated.
     */
    public static final String ENDPOINTIPV6 = "ipv6";
    /**
     * EndPoint policy ID attribute.
     */
    public static final String ENDPOINTPOLICYID = "EndPointPolicyID";
    /**
     * EndPoint policy attribute. Not a persistent attribute.
     */
    public static final String ENDPOINTPOLICY = "EndPointPolicy";
    /**
     * Matched policy ID. May be different than EndPointPolicy if profiled by
     * and exception rule.
     */
    public static final String MATCHEDPOLICYID = "MatchedPolicyID";
    /**
     * Matched policy. May be different than EndPointPolicy if profiled by and
     * exception rule. Not a persistent attribute.
     */
    public static final String MATCHEDPOLICY = "MatchedPolicy";
    /**
     * The certainty value for the policy match. It is the sum of all the rules
     * that matched.
     */
    public static final String MATCHEDVALUE = "Total Certainty Factor";
    /**
     * Mac address attribute name.
     */
    public static final String MACADDRESS = "MACAddress";
    /**
     * OUI attribute name.
     */
    public static final String OUI = "OUI";
    /**
     * Endpoint is statically assigned.
     */
    public static final String STATICASSIGNEMENT = "StaticAssignment";
    /**
     * EndPoint profiler server attribute name.
     */
    public static final String ENDPOINTPROFILERSERVER = "EndPointProfilerServer";
    /**
     * EndPoint source attribute name.
     */
    public static final String ENDPOINTSOURCE = "EndPointSource";
    /**
     * NAS IP address (radius) attribute name.
     */
    public static final String NASIPADDRESS = "NAS-IP-Address";
    /**
     * NAD IP address (snmp) attribute name.
     */
    public static final String NADADDRESS = "NADAddress";
    /**
     * Start of ARP related attributes. *
     */
    /**
     * Portal Username.
     */
    public static final String PORTALUSER = "PortalUser";
    /**
     * Unique Subject ID (uniquely identifies a portal user across all identity stores).
     */
    public static final String UNIQUESUBJECTID = "UniqueSubjectID";
    /**
     * Identity store name.
     */
    public static final String IDSTORENAME = "IdentityStoreName";
    /**
     * Identity store GUID.
     */
    public static final String IDSTOREGUID = "IdentityStoreGUID";
    /**
     * Device registration status: "registered", "pending", "blacklisted",
     * "stolen".
     */
    public static final String DEVICEREGSTATUS = "DeviceRegistrationStatus";

    /**
     * Previous Device registration status: "registered", "pending", "blacklisted",
     * "stolen".
     */
    public static final String PREVIOUSDEVICEREGSTATUS = "PreviousDeviceRegistrationStatus";
    /**
     * Posture Applicable status: "No", "Yes".
     */
    public static final String POSTUREAPPLICABLE = "PostureApplicable";
    /**
     * Posture expiration time.
     */
    public static final String POSTURE_EXPIRY = "PostureExpiry";

    /**
     * Posture Last compliant expiration time.
     * Used to determine if device is allow grace period
     */
    public static final String POSTURE_LAST_COMPLIANT_EXPIRY = "PostureLastCompliantExpiry";

    /**
     * Device registration status timestamp. Miliseconds since epoch.
     */
    public static final String DEVICEREGSTIME = "RegistrationTimeStamp";
    /**
     * BYOD registered : Yes, No or Null.
     */
    public static final String BYODREGISTERED = "BYODRegistration";
    /**
     * Operating system version.
     */
    public static final String OSVERSION = "OS Version";
    /**
     * Operating system. As indicated by the user agent.
     *
     * @since 1.3
     */
    public static final String OPERATINGSYSTEM = "OperatingSystem";
    /**
     * User agent attribute name. Set by guest.
     */
    public static final String USERAGENT = "User-Agent";
    /**
     * Device identifier (for iPhone - UDID).
     */
    public static final String DEVICEIDENTIFIER = "Device Identifier";
    /**
     * Product name.
     */
    public static final String PRODUCT = "Product";
    /**
     * Device name.
     */
    public static final String DEVICENAME = "Device Name";
    /**
     * Certificate serial number.
     */
    public static final String CERTSERIAL = "Certificate Serial Number";
    /**
     * Certificate issuer name.
     */
    public static final String CERTISSUER = "Certificate Issuer Name";
    /**
     * Certificate issue date.
     */
    public static final String CERTSISSUEDATE = "Certificate Issue Date";
    /**
     * Certificate expiration date.
     */
    public static final String CERTEXPIRDATE = "Certificate Expiration Date";
    /**
     * Zero addrs sent from client.
     */
    public static final String ZEROADDR = "0.0.0.0";
    /**
     * End of ARP related attributes. *
     */
    /**
     * Guest related attributes
     */
    /**
     * True or false whether AUP was accepted on this device.
     */
    public static final String AUP_ACCEPTED = "AUPAccepted";
    /**
     * Time stamp (msec since epoch for last AUP acceptance.
     */
    public static final String LAST_AUP_ACCEPTED_TIMESTAMP = "LastAUPAccepted";
    /**
     * End of guest related attributes.
     */
    /**
     * MDM related attributes.
     *
     *
     * @since 1.2
     */
    /**
     * Manufacturer.
     */
    public static final String MDMMANUFACTURER = "MDMManufacturer";
    /**
     * Model name.
     */
    public static final String MDMMODEL = "MDMModel";
    /**
     * MDM Profiler.
     *
     * @since 1.3
     */
    public static final String MDMPROVIDER = "MDMProvider";
    /**
     * ID of the MDM server used for enrolling the device.
     *
     * @since 1.4
     */
    public static final String MDMSERVERID = "MDMServerID";

    /**
     * name of the MDM server used for enrolling the device.
     */
    public static final String MDMSERVERNAME = "MDMServerName";
    /**
     * IMEI - International Mobile Equipment Identity.
     */
    public static final String MDMIMEI = "MDMImei";
    /**
     * Phone identifier. Different for each carrier/ type of phone.
     */
    public static final String PHONEID = "PhoneID";
    /**
     * Indication of the data used as the phone ID.
     */
    public static final String PHONEIDTYPE = "PhoneIDType";

    public enum PhoneIDType {
        UNKNOWN,
        MEID,
        UDID,
        IMEI,
        SERIAL_NUMBER,
    }

    /**
     * Serial Number.
     */
    public static final String MDMSERIALNUM = "MDMSerialNumber";
    /**
     * MDM operating system version.
     */
    public static final String MDMOSVERSION = "MDMOSVersion";
    /**
     * Phone number.
     */
    public static final String MDMPHONENUM = "MDMPhoneNumber";
    /**
     * MDM is enrolled.
     */
    public static final String MDM_ENROLLED = "MDMEnrolled";
    /**
     * MDM is compliant.
     */
    public static final String MDM_COMPLIANT = "MDMCompliant";
    public static final String MDM_COMPLIANT_FAILURE_REASON = "MDMCompliantFailureReason";
    /**
     * MDM is disk encrypted.
     */
    public static final String MDM_DISKENCRYPTED = "MDMDiskEncrypted";
    /**
     * MDM is device jail broken (root access).
     */
    public static final String MDM_JAILBROKEN = "MDMJailBroken";
    /**
     * MDM is server reachable.
     */
    public static final String MDM_SERVERREACHABLE = "MDMServerReachable";
    /**
     * MDM is pin locked.
     */
    public static final String MDM_PINLOCKSET = "MDMPinLockSet";
    /**
     * MDM - last checkin timestamp.
     */
    public static final String MDM_LASTCHECKIN_TIMESTAMP = "MDMLastCheckinTimeStamp";
    /**
     * MDM update timestamp.
     */
    public static final String MDM_UPDATETIMESTAMP = "MDMUpdateTime";
    /**
     * MDM is user notified.
     */
    public static final String MDM_USER_NOTIFIED = "MDMUserNotified";
    /**
     * Posture Compliance
     */
    public static final String POSTURE_STATUS = "PostureStatus";

    /**
     * End of MDM related attributes.
     */
    /**
     * Interface index attribute name.
     */
    public static final String IFINDEX = "ifIndex";
    /**
     * Unknown Endpoint policy.
     */
    public static final String UNKNOWN = "Unknown";
    /**
     * Default value for matched policy metrics.
     */
    public static final String NOTMATCHED = "0";
    /**
     * IdentityGroup that this endpoint belongs to.
     */
    public static final String IDENTITYGROUPID = "IdentityGroupID";
    /**
     * IdentityGroup name that this endpoint belongs to. Not a persistent
     * attribute.
     */
    public static final String IDENTITYGROUP = "IdentityGroup";
    /**
     * Indication whether the identity group assignment is static - manually
     * assigned, or dynamic - based on profiling.
     */
    public static final String STATICGRPASSIGNMENT = "StaticGroupAssignment";
    /**
     * Indicator of the endpoint policy version.
     */
    public static final String POLICYVERSION = "PolicyVersion";
    /**
     * Count of nmap scans.
     */
    public static final String NMAPSCANCOUNT = "NmapScanCount";
    /**
     * Timestamp of last nmap scan in milisec since epoch.
     */
    public static final String NMAPSCANTIME = "LastNmapScanTime";
    /**
     * The ID of the subnet scan that found this endpoint.
     */
    public static final String NMAPSUBNETSCANID = "NmapSubnetScanID";
    /**
     * Indicator of the time to first collection.
     */
    public static final String FIRSTCOLLECTION = "FirstCollection";
    /**
     * Indicator of the time to first profile.
     */
    public static final String TIMETOPROFILE = "TimeToProfile";
    /**
     * Description of the endpoint.
     */
    public static final String DESCRIPTION = "Description";
    /**
     * AC User Agent of the endpoint.
     */
    public static final String AC_USER_AGENT = "AC_User_Agent";
    /**
     * host name.
     */
    public static final String HOSTNAME = "host-name";
    /**
     * Activity timestamp.
     */
    public static final String ACTIVITYTIME = "LastActivity";
    /*
     * Create timestamp. Non-persistent attribute.
     */
    public static final String CREATETIME = "CreateTime";
    /*
     * EDF Create timestamp. Non-persistent attribute.
     */
    public static final String EDF_CREATETIME = "EDFCreateTime";
    /*
     * EDF Update timestamp. Non-persistent attribute.
     */
    public static final String EDF_UPDATETIME = "EDFupdateTime";
    /**
     * collection days.
     */
    public static final String ENDPOINTCOLLECTDAYS = "InactiveDays";
    /**
     * creation days.
     */
    public static final String ENDPOINTCREATEDAYS = "ElapsedDays";
    /**
     * logical profiles this endpoint belongs to.
     */
    public static final String LOGICALPROFILES = "LogicalProfile";
    /**
     * update time.
     */
    public static final String UPDATETIME = "UpdateTime";
    /**
     * Cache update time. Non persistence or GUI visible attribute.
     */
    public static final String CACHEUPDATETIME = "CacheUpdateTime";
    /**
     * ACIDex device platform.
     */
    public static final String DEVICE_PLATFORM = "device-platform";

    public static final String IOT_ASSET_RETRIEVED_FROM = "iotAssetRetrievedFrom";

    public static final String IOT_ASSET_DEVICE_TYPE = "iotAssetDeviceType";

    public static final String IOT_ASSET_PRODUCT_NAME = "iotAssetProductName";

    public static final String IOT_ASSET_VENDOR_ID = "iotAssetVendorID";

    public static final String IOT_ASSET_PRODUCT_CODE = "iotAssetProductCode";

    public static final String IOT_ASSET_SERIAL_NUMBER = "iotAssetSerialNumber";

    public static final String IOT_ASSET_TRUST_LEVEL = "iotAssetTrustLevel";

    public static final String AD_LAST_FETCH_TIME = "AD-Last-Fetch-Time";

    public static final String AD_FETCH_HOST_NAME = "AD-Fetch-Host-Name";

    public static final String USER_FETCH_PASSIVEID_USERNAME = "USER_FETCH_PASSIVEID_USERNAME";

    public static final String USER_FETCH_USERNAME = "USER_FETCH_USERNAME";

    public static final String USER_AD_LAST_FETCH_TIME = "USER_AD_LAST_FETCH_TIME";

    /**
     * Manufacturer Usage Description URI.
     */
    public static final String MUD_URL = "MUD-URL";

    public static final String IOT_MANUFACTURER = "IOT-manufacturer";

    public static final String IOT_TYPE = "IOT-type";

    public static final String IOT_MODEL = "IOT-model";

    public static final String IOT_VERSION = "IOT-version";


    /**
     * getters and setters for user AD attributes
     *
     * @return
     */
    public String getAdUserfetchPassiveidUsername() {
        return attributes.get(USER_FETCH_PASSIVEID_USERNAME);
    }

    public void setAdUserfetchPassiveidUsername(String name) {
        attributes.put(USER_FETCH_PASSIVEID_USERNAME, name);
    }

    public String getAdUserfetchUsername() {
        return attributes.get(USER_FETCH_USERNAME);
    }

    public void setAdUserfetchUsername(String name) {
        attributes.put(USER_FETCH_USERNAME, name);
    }

    /**
     * Computed operating system.
     */
    public static final String OPERATING_SYSTEM_RESULT = "operating-system-result";
    /**
     * Computed compliance status.
     */
    public static final String DEVICE_COMPLIANCE = "DeviceCompliance";

    /**
     * The maximum number of ipv6 addresses
     */
    public static final int MAX_NUM_IPV6 = 8;

    /**
     * hashmap of the attributes associated with the endpoint.
     */
    private ConcurrentHashMap<String, String> attributes = new ConcurrentHashMap<String, String>();
    /**
     * custom attribute map
     */
    private Map<String, String> customAttributes = new ConcurrentHashMap<String, String>();
    /**
     * Set of changes attributes
     */
    private Set<String> modifiedAttributeSet = null;
    /**
     *
     */
    public static List<String> CUSTATTRSOURCE = Arrays.asList("GUI", "REST", "CSV File Import", "PXGRIDPROBE");
    /**
     * Skip profiling once.
     */
    private boolean skipProfiling = false;

    /**
     * The DHCP ciaddr attribute.
     */
    public static final String CIADDR = "ciaddr";

    /**
     * Set of ipv6 attributes.
     */
    Set<String> ipv6Addresses = new LinkedHashSet<String>();

    /**
     * USER Agent.
     */
    public static final String USER_AGENT = "User-Agent";
    /**
     * Cache only attribute to check if its guest Endpoint.
     */
    public static final String IS_REGISTERED_ENDPOINT = "IsRegistered";
    /**
     * USER Agent.
     */
    public static final String IGNORED_USER_AGENT = "Ignored-User-Agent";

    /**
     * Some missing new Attributes introduced for import/export
     */
    public static final String DEVICE_TYPE = "Device Type";

    public static final String EMAIL_ADDRESS = "EmailAddress";

    public static final String FIRST_NAME = "FirstName";

    public static final String LAST_NAME = "LastName";

    public static final String LOCATION = "Location";

    public static final String PORTALUSER_FIRSTNAME = "PortalUser.FirstName";

    public static final String PORTALUSER_LASTNAME = "PortalUser.LastName";

    public static final String PORTALUSER_EMAILADDRESS = "PortalUser.EmailAddress";

    public static final String PORTALUSER_PHONE = "PortalUser.PhoneNumber";

    public static final String PORTALUSER_GUEST_TYPE = "PortalUser.GuestType";

    public static final String PORTALUSER_GUESTSTATUS = "PortalUser.GuestStatus";

    public static final String PORTALUSER_LOCATION = "PortalUser.Location";

    public static final String PORTALUSER_GUESTSPONSOR = "PortalUser.GuestSponsor";

    public static final String PORTALUSER_CREATIONTYPE = "PortalUser.CreationType";

    public static final String RADIUS_USERNAME = "User-Name";

    private boolean persistToDb = true;

    /**
     * Attributes that are represented by columns in EDF, and therefore are
     * search-able.
     */
    static final String[] SEARCHABLE_ATTRS = {
            //EndPoint.ENDPOINTIP,
            EndPoint.ENDPOINTPOLICY,
            EndPoint.MATCHEDVALUE,
            EndPoint.STATICASSIGNEMENT,
            EndPoint.STATICGRPASSIGNMENT,
            EndPoint.IDENTITYGROUPID,
            EndPoint.MATCHEDPOLICYID,
            EndPoint.NMAPSUBNETSCANID,
            EndPoint.PORTALUSER,
            EndPoint.POSTUREAPPLICABLE,
            EndPoint.DEVICEREGSTATUS,
            EndPoint.BYODREGISTERED,
            EndPoint.HOSTNAME,
            EndPoint.DEVICEIDENTIFIER,
            EndPoint.UNIQUESUBJECTID
    };

    /**
     * Attributes that are significant to trigger saving to PAP.
     * But are not in a separate column.
     */
    public final String[] SIGNIFICANT_NON_SEARCHABLE_ATTRS = {
            EndPoint.ANOMALOUSBEHAVIOUR
    };

    /**
     * Guest Attributes that are significant to trigger saving to PAP. But are not
     * in a separate column.
     */
    public final String[] GUEST_SIGNIFICANT_NON_SEARCHABLE_ATTRS = {
            EndPoint.AUP_ACCEPTED,
            EndPoint.LAST_AUP_ACCEPTED_TIMESTAMP
    };

    /**
     * List of attributes that are not initialized by the probe, and therefore
     * should be removed before merges.
     */
    public static final HashSet<String> NOT_MERGED_ATTR = new HashSet<String>() {
        {
            add(EndPoint.ENDPOINTPOLICY);
            add(EndPoint.ENDPOINTPOLICYID);
            add(EndPoint.IDENTITYGROUP);
            add(EndPoint.IDENTITYGROUPID);
            add(EndPoint.MATCHEDPOLICY);
            add(EndPoint.MATCHEDPOLICYID);
            add(EndPoint.STATICGRPASSIGNMENT);
            add(EndPoint.STATICASSIGNEMENT);
            add(EndPoint.MATCHEDVALUE);
            add(EndPoint.UPDATETIME);
        }
    };

    /**
     * List of attributes that are not initialized by the guest source, and therefore
     * should be removed before merges.
     */
    public static final HashSet<String> NOT_GUEST_MERGED_ATTR = new HashSet<String>() {

        {
            add(EndPoint.ENDPOINTPOLICY);
            add(EndPoint.ENDPOINTPOLICYID);
            add(EndPoint.MATCHEDPOLICY);
            add(EndPoint.MATCHEDPOLICYID);
            add(EndPoint.STATICASSIGNEMENT);
            add(EndPoint.MATCHEDVALUE);
            add(EndPoint.UPDATETIME);
        }
    };

    public static final String ANOMALOUSBEHAVIOUR = "AnomalousBehaviour";

    /**
     * Device registration status for BYOD.
     */
    public static enum REGISTRATIONSTATUS {

        /**
         * notregistered.
         */
        NOTREGISTERED("NotRegistered", 0),
        /**
         * pending.
         */
        PENDING("Pending", 1),
        /**
         * registered.
         */
        REGISTERED("Registered", 2),
        /**
         * blacklisted.
         */
        BLACKLISTED("Blacklisted", 3),
        /**
         * stolen.
         */
        STOLEN("Stolen", 4),
        /**
         * lost.
         */
        LOST("Lost", 5),
        /**
         * Pending Unenroll
         */
        PENDING_UNENROLL("PendingUnEnroll", 6),
        /**
         * Pending FullWipe
         */
        PENDING_FULLWIPE("PendingFullWipe", 7);

        String name;
        int value;

        REGISTRATIONSTATUS(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }


        /**
         * @return string representation of the enum.
         */
        @Override
        public String toString() {
            return getName();
        }
    }

    public static int getMillisPerDay() {
        return MILLIS_PER_DAY;
    }

    public static String[] getIpAttrs() {
        return ipAttrs;
    }

    public static String[] getOsAttrs() {
        return OS_ATTRS;
    }

    public static String getENDPOINTIP() {
        return ENDPOINTIP;
    }

    public static String getENDPOINTIPV6() {
        return ENDPOINTIPV6;
    }

    public static String getENDPOINTPOLICYID() {
        return ENDPOINTPOLICYID;
    }

    public static String getENDPOINTPOLICY() {
        return ENDPOINTPOLICY;
    }

    public static String getMATCHEDPOLICYID() {
        return MATCHEDPOLICYID;
    }

    public static String getMATCHEDPOLICY() {
        return MATCHEDPOLICY;
    }

    public void setMac(String pMac)  {
        attributes.put(MACADDRESS, pMac);
    }

    public String getMac() {
        return (String) attributes.get(MACADDRESS);
    }

    public static String getMATCHEDVALUE() {
        return MATCHEDVALUE;
    }

    public static String getMACADDRESS() {
        return MACADDRESS;
    }

    public static String getOUI() {
        return OUI;
    }

    public static String getSTATICASSIGNEMENT() {
        return STATICASSIGNEMENT;
    }

    public static String getENDPOINTPROFILERSERVER() {
        return ENDPOINTPROFILERSERVER;
    }

    public static String getENDPOINTSOURCE() {
        return ENDPOINTSOURCE;
    }

    public static String getNASIPADDRESS() {
        return NASIPADDRESS;
    }

    public static String getNADADDRESS() {
        return NADADDRESS;
    }

    public static String getPORTALUSER() {
        return PORTALUSER;
    }

    public static String getUNIQUESUBJECTID() {
        return UNIQUESUBJECTID;
    }

    public static String getIDSTORENAME() {
        return IDSTORENAME;
    }

    public static String getIDSTOREGUID() {
        return IDSTOREGUID;
    }

    public static String getDEVICEREGSTATUS() {
        return DEVICEREGSTATUS;
    }

    public static String getPREVIOUSDEVICEREGSTATUS() {
        return PREVIOUSDEVICEREGSTATUS;
    }

    public static String getPOSTUREAPPLICABLE() {
        return POSTUREAPPLICABLE;
    }

    public static String getPostureExpiry() {
        return POSTURE_EXPIRY;
    }

    public static String getPostureLastCompliantExpiry() {
        return POSTURE_LAST_COMPLIANT_EXPIRY;
    }

    public static String getDEVICEREGSTIME() {
        return DEVICEREGSTIME;
    }

    public static String getBYODREGISTERED() {
        return BYODREGISTERED;
    }

    public static String getOSVERSION() {
        return OSVERSION;
    }

    public static String getOPERATINGSYSTEM() {
        return OPERATINGSYSTEM;
    }

    public static String getUSERAGENT() {
        return USERAGENT;
    }

    public static String getDEVICEIDENTIFIER() {
        return DEVICEIDENTIFIER;
    }

    public static String getPRODUCT() {
        return PRODUCT;
    }

    public static String getDEVICENAME() {
        return DEVICENAME;
    }

    public static String getCERTSERIAL() {
        return CERTSERIAL;
    }

    public static String getCERTISSUER() {
        return CERTISSUER;
    }

    public static String getCERTSISSUEDATE() {
        return CERTSISSUEDATE;
    }

    public static String getCERTEXPIRDATE() {
        return CERTEXPIRDATE;
    }

    public static String getZEROADDR() {
        return ZEROADDR;
    }

    public static String getAupAccepted() {
        return AUP_ACCEPTED;
    }

    public static String getLastAupAcceptedTimestamp() {
        return LAST_AUP_ACCEPTED_TIMESTAMP;
    }

    public static String getMDMMANUFACTURER() {
        return MDMMANUFACTURER;
    }

    public static String getMDMMODEL() {
        return MDMMODEL;
    }

    public static String getMDMPROVIDER() {
        return MDMPROVIDER;
    }

    public static String getMDMSERVERID() {
        return MDMSERVERID;
    }

    public static String getMDMSERVERNAME() {
        return MDMSERVERNAME;
    }

    public static String getMDMIMEI() {
        return MDMIMEI;
    }

    public static String getPHONEID() {
        return PHONEID;
    }

    public static String getPHONEIDTYPE() {
        return PHONEIDTYPE;
    }

    public static String getMDMSERIALNUM() {
        return MDMSERIALNUM;
    }

    public static String getMDMOSVERSION() {
        return MDMOSVERSION;
    }

    public static String getMDMPHONENUM() {
        return MDMPHONENUM;
    }

    public static String getMdmEnrolled() {
        return MDM_ENROLLED;
    }

    public static String getMdmCompliant() {
        return MDM_COMPLIANT;
    }

    public static String getMdmCompliantFailureReason() {
        return MDM_COMPLIANT_FAILURE_REASON;
    }

    public static String getMdmDiskencrypted() {
        return MDM_DISKENCRYPTED;
    }

    public static String getMdmJailbroken() {
        return MDM_JAILBROKEN;
    }

    public static String getMdmServerreachable() {
        return MDM_SERVERREACHABLE;
    }

    public static String getMdmPinlockset() {
        return MDM_PINLOCKSET;
    }

    public static String getMdmLastcheckinTimestamp() {
        return MDM_LASTCHECKIN_TIMESTAMP;
    }

    public static String getMdmUpdatetimestamp() {
        return MDM_UPDATETIMESTAMP;
    }

    public static String getMdmUserNotified() {
        return MDM_USER_NOTIFIED;
    }

    public static String getPostureStatus() {
        return POSTURE_STATUS;
    }

    public static String getIFINDEX() {
        return IFINDEX;
    }

    public static String getUNKNOWN() {
        return UNKNOWN;
    }

    public static String getNOTMATCHED() {
        return NOTMATCHED;
    }

    public static String getIDENTITYGROUPID() {
        return IDENTITYGROUPID;
    }

    public static String getIDENTITYGROUP() {
        return IDENTITYGROUP;
    }

    public static String getSTATICGRPASSIGNMENT() {
        return STATICGRPASSIGNMENT;
    }

    public static String getPOLICYVERSION() {
        return POLICYVERSION;
    }

    public static String getNMAPSCANCOUNT() {
        return NMAPSCANCOUNT;
    }

    public static String getNMAPSCANTIME() {
        return NMAPSCANTIME;
    }

    public static String getNMAPSUBNETSCANID() {
        return NMAPSUBNETSCANID;
    }

    public static String getFIRSTCOLLECTION() {
        return FIRSTCOLLECTION;
    }

    public static String getTIMETOPROFILE() {
        return TIMETOPROFILE;
    }

    public static String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public static String getAcUserAgent() {
        return AC_USER_AGENT;
    }

    public static String getHOSTNAME() {
        return HOSTNAME;
    }

    public static String getACTIVITYTIME() {
        return ACTIVITYTIME;
    }

    public static String getCREATETIME() {
        return CREATETIME;
    }

    public static String getEdfCreatetime() {
        return EDF_CREATETIME;
    }

    public static String getEdfUpdatetime() {
        return EDF_UPDATETIME;
    }

    public static String getENDPOINTCOLLECTDAYS() {
        return ENDPOINTCOLLECTDAYS;
    }

    public static String getENDPOINTCREATEDAYS() {
        return ENDPOINTCREATEDAYS;
    }

    public static String getLOGICALPROFILES() {
        return LOGICALPROFILES;
    }

    public static String getUPDATETIME() {
        return UPDATETIME;
    }

    public static String getCACHEUPDATETIME() {
        return CACHEUPDATETIME;
    }

    public static String getDevicePlatform() {
        return DEVICE_PLATFORM;
    }

    public static String getIotAssetRetrievedFrom() {
        return IOT_ASSET_RETRIEVED_FROM;
    }

    public static String getIotAssetDeviceType() {
        return IOT_ASSET_DEVICE_TYPE;
    }

    public static String getIotAssetProductName() {
        return IOT_ASSET_PRODUCT_NAME;
    }

    public static String getIotAssetVendorId() {
        return IOT_ASSET_VENDOR_ID;
    }

    public static String getIotAssetProductCode() {
        return IOT_ASSET_PRODUCT_CODE;
    }

    public static String getIotAssetSerialNumber() {
        return IOT_ASSET_SERIAL_NUMBER;
    }

    public static String getIotAssetTrustLevel() {
        return IOT_ASSET_TRUST_LEVEL;
    }

    public static String getAdLastFetchTime() {
        return AD_LAST_FETCH_TIME;
    }

    public static String getAdFetchHostName() {
        return AD_FETCH_HOST_NAME;
    }

    public static String getUserFetchPassiveidUsername() {
        return USER_FETCH_PASSIVEID_USERNAME;
    }

    public static String getUserFetchUsername() {
        return USER_FETCH_USERNAME;
    }

    public static String getUserAdLastFetchTime() {
        return USER_AD_LAST_FETCH_TIME;
    }

    public static String getMudUrl() {
        return MUD_URL;
    }

    public static String getIotManufacturer() {
        return IOT_MANUFACTURER;
    }

    public static String getIotType() {
        return IOT_TYPE;
    }

    public static String getIotModel() {
        return IOT_MODEL;
    }

    public static String getIotVersion() {
        return IOT_VERSION;
    }

    public static String getOperatingSystemResult() {
        return OPERATING_SYSTEM_RESULT;
    }

    public static String getDeviceCompliance() {
        return DEVICE_COMPLIANCE;
    }

    public static int getMaxNumIpv6() {
        return MAX_NUM_IPV6;
    }

    public ConcurrentHashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(ConcurrentHashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    public Map<String, String> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(Map<String, String> customAttributes) {
        this.customAttributes = customAttributes;
    }

    public Set<String> getModifiedAttributeSet() {
        return modifiedAttributeSet;
    }

    public void setModifiedAttributeSet(Set<String> modifiedAttributeSet) {
        this.modifiedAttributeSet = modifiedAttributeSet;
    }

    public static List<String> getCUSTATTRSOURCE() {
        return CUSTATTRSOURCE;
    }

    public static void setCUSTATTRSOURCE(List<String> CUSTATTRSOURCE) {
        EndPoint.CUSTATTRSOURCE = CUSTATTRSOURCE;
    }

    public boolean isSkipProfiling() {
        return skipProfiling;
    }

    public void setSkipProfiling(boolean skipProfiling) {
        this.skipProfiling = skipProfiling;
    }

    public static String getCIADDR() {
        return CIADDR;
    }

    public Set<String> getIpv6Addresses() {
        return ipv6Addresses;
    }

    public void setIpv6Addresses(Set<String> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
    }

    public static String getUserAgent() {
        return USER_AGENT;
    }

    public static String getIsRegisteredEndpoint() {
        return IS_REGISTERED_ENDPOINT;
    }

    public static String getIgnoredUserAgent() {
        return IGNORED_USER_AGENT;
    }

    public static String getDeviceType() {
        return DEVICE_TYPE;
    }

    public static String getEmailAddress() {
        return EMAIL_ADDRESS;
    }

    public static String getFirstName() {
        return FIRST_NAME;
    }

    public static String getLastName() {
        return LAST_NAME;
    }

    public static String getLOCATION() {
        return LOCATION;
    }

    public static String getPortaluserFirstname() {
        return PORTALUSER_FIRSTNAME;
    }

    public static String getPortaluserLastname() {
        return PORTALUSER_LASTNAME;
    }

    public static String getPortaluserEmailaddress() {
        return PORTALUSER_EMAILADDRESS;
    }

    public static String getPortaluserPhone() {
        return PORTALUSER_PHONE;
    }

    public static String getPortaluserGuestType() {
        return PORTALUSER_GUEST_TYPE;
    }

    public static String getPortaluserGueststatus() {
        return PORTALUSER_GUESTSTATUS;
    }

    public static String getPortaluserLocation() {
        return PORTALUSER_LOCATION;
    }

    public static String getPortaluserGuestsponsor() {
        return PORTALUSER_GUESTSPONSOR;
    }

    public static String getPortaluserCreationtype() {
        return PORTALUSER_CREATIONTYPE;
    }

    public static String getRadiusUsername() {
        return RADIUS_USERNAME;
    }

    public boolean isPersistToDb() {
        return persistToDb;
    }

    public void setPersistToDb(boolean persistToDb) {
        this.persistToDb = persistToDb;
    }

    public static String[] getSearchableAttrs() {
        return SEARCHABLE_ATTRS;
    }

    public String[] getSIGNIFICANT_NON_SEARCHABLE_ATTRS() {
        return SIGNIFICANT_NON_SEARCHABLE_ATTRS;
    }

    public String[] getGUEST_SIGNIFICANT_NON_SEARCHABLE_ATTRS() {
        return GUEST_SIGNIFICANT_NON_SEARCHABLE_ATTRS;
    }

    public static HashSet<String> getNotMergedAttr() {
        return NOT_MERGED_ATTR;
    }

    public static HashSet<String> getNotGuestMergedAttr() {
        return NOT_GUEST_MERGED_ATTR;
    }

    public static String getANOMALOUSBEHAVIOUR() {
        return ANOMALOUSBEHAVIOUR;
    }
}