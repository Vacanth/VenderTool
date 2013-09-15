package com.vendertool.sharedtypes.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;

import org.apache.log4j.Logger;

@XmlEnum
public enum CountryEnum {
	ALL								("ALL", 											"AA",		"AAA",	  1), 
	
	
	AALAND_ISLANDS					("AALAND ISLANDS",                                  "AX",      "ALA",     248),
	AFGHANISTAN						("AFGHANISTAN",                                     "AF",      "AFG",     4),
	ALBANIA							("ALBANIA",                                         "AL",      "ALB",     8),
	ALGERIA							("ALGERIA",                                         "DZ",      "DZA",     12),
	AMERICAN_SAMOA					("AMERICAN SAMOA",                                  "AS",      "ASM",     16),
	ANDORRA							("ANDORRA",                                         "AD",      "AND",     20),
	ANGOLA							("ANGOLA",                                          "AO",      "AGO",     24),
	ANGUILLA						("ANGUILLA",                                        "AI",      "AIA",     660),
	ANTARCTICA						("ANTARCTICA",                                      "AQ",      "ATA",     10),
	ANTIGUA_AND_BARBUDA				("ANTIGUA AND BARBUDA",                             "AG",      "ATG",     28),
	ARGENTINA						("ARGENTINA",                                       "AR",      "ARG",     32),
	ARMENIA							("ARMENIA",                                         "AM",      "ARM",     51),
	ARUBA							("ARUBA",                                           "AW",      "ABW",     533),
	AUSTRALIA						("AUSTRALIA",                                       "AU",      "AUS",     36),
	AUSTRIA							("AUSTRIA",                                         "AT",      "AUT",     40),
	AZERBAIJAN						("AZERBAIJAN",                                      "AZ",      "AZE",     31),
	BAHAMAS							("BAHAMAS",                                         "BS",      "BHS",     44),
	BAHRAIN							("BAHRAIN",                                         "BH",      "BHR",     48),
	BANGLADESH						("BANGLADESH",                                      "BD",      "BGD",     50),
	BARBADOS						("BARBADOS",                                        "BB",      "BRB",     52),
	BELARUS							("BELARUS",                                         "BY",      "BLR",     112),
	BELGIUM							("BELGIUM",                                         "BE",      "BEL",     56),
	BELIZE							("BELIZE",                                          "BZ",      "BLZ",     84),
	BENIN							("BENIN",                                           "BJ",      "BEN",     204),
	BERMUDA							("BERMUDA",                                         "BM",      "BMU",     60),
	BHUTAN							("BHUTAN",                                          "BT",      "BTN",     64),
	BOLIVIA							("BOLIVIA",                                         "BO",      "BOL",     68),
	BOSNIA_HERZEGOWINA				("BOSNIA AND HERZEGOWINA",                          "BA",      "BIH",     70),
	BOTSWANA						("BOTSWANA",                                        "BW",      "BWA",     72),
	BOUVET_ISLAND					("BOUVET ISLAND",                                   "BV",      "BVT",     74),
	BRAZIL							("BRAZIL",                                          "BR",      "BRA",     76),
	BRITISH_INDIAN_OCEAN			("BRITISH INDIAN OCEAN TERRITORY",                  "IO",      "IOT",     86),
	BRUNEI_DARUSSALAM				("BRUNEI DARUSSALAM",                               "BN",      "BRN",     96),
	BULGARIA						("BULGARIA",                                        "BG",      "BGR",     100),
	BURKINA_FASO					("BURKINA FASO",                                    "BF",      "BFA",     854),
	BURUNDI							("BURUNDI",                                         "BI",      "BDI",     108),
	CAMBODIA						("CAMBODIA",                                        "KH",      "KHM",     116),
	CAMEROON						("CAMEROON",                                        "CM",      "CMR",     120),
	CANADA							("CANADA",                                          "CA",      "CAN",     124),
	CAPE_VERDE						("CAPE VERDE",                                      "CV",      "CPV",     132),
	CAYMAN_ISLANDS					("CAYMAN ISLANDS",                                  "KY",      "CYM",     136),
	CENTRAL_AFRICAN_REPUBLIC		("CENTRAL AFRICAN REPUBLIC",                        "CF",      "CAF",     140),
	CHAD							("CHAD",                                            "TD",      "TCD",     148),
	CHILE							("CHILE",                                           "CL",      "CHL",     152),
	CHINA							("CHINA",                                           "CN",      "CHN",     156),
	CHRISTMAS_ISLAND				("CHRISTMAS ISLAND",                                "CX",      "CXR",     162),
	COCOS_ISLANDS					("COCOS (KEELING) ISLANDS",                         "CC",      "CCK",     166),
	COLOMBIA						("COLOMBIA",                                        "CO",      "COL",     170),
	COMOROS							("COMOROS",                                         "KM",      "COM",     174),
	CONGO_DEMOCRATIC_REPUBLIC		("CONGO, Democratic Republic of (was Zaire)",       "CD",      "COD",     180),
	CONGO_REPUBLIC_of				("CONGO, Republic of",                              "CG",      "COG",     178),
	COOK_ISLANDS					("COOK ISLANDS",                                    "CK",      "COK",     184),
	COSTA_RICA						("COSTA RICA",                                      "CR",      "CRI",     188),
	COTE_DIVOIRE					("COTE D'IVOIRE",                                   "CI",      "CIV",     384),
	CROATIA							("CROATIA (local name: Hrvatska)",                  "HR",      "HRV",     191),    
	CUBA							("CUBA",                                            "CU",      "CUB",     192),
	CYPRUS							("CYPRUS",                                          "CY",      "CYP",     196),
	CZECH_REPUBLIC					("CZECH REPUBLIC",                                  "CZ",      "CZE",     203),
	DENMARK							("DENMARK",                                         "DK",      "DNK",     208),
	DJIBOUTI						("DJIBOUTI",                                        "DJ",      "DJI",     262),
	DOMINICA						("DOMINICA",                                        "DM",      "DMA",     212),
	DOMINICAN_REPUBLIC				("DOMINICAN REPUBLIC",                              "DO",      "DOM",     214),
	ECUADOR							("ECUADOR",                                         "EC",      "ECU",     218),
	EGYPT							("EGYPT",                                           "EG",      "EGY",     818),
	EL_SALVADOR						("EL SALVADOR",                                     "SV",      "SLV",     222),
	EQUATORIAL_GUINEA				("EQUATORIAL GUINEA",                               "GQ",      "GNQ",     226),
	ERITREA							("ERITREA",                                         "ER",      "ERI",     232),
	ESTONIA							("ESTONIA",                                         "EE",      "EST",     233),
	ETHIOPIA						("ETHIOPIA",                                        "ET",      "ETH",     231),
	FALKLAND_ISLANDS				("FALKLAND ISLANDS (MALVINAS)",                     "FK",      "FLK",     238),
	FAROE_ISLANDS					("FAROE ISLANDS",                                   "FO",      "FRO",     234),
	FIJI							("FIJI",                                            "FJ",      "FJI",     242),
	FINLAND							("FINLAND",                                         "FI",      "FIN",     246),
	FRANCE							("FRANCE",                                          "FR",      "FRA",     250),
	FRENCH_GUIANA					("FRENCH GUIANA",                                   "GF",      "GUF",     254),
	FRENCH_POLYNESI					("FRENCH POLYNESIA",                                "PF",      "PYF",     258),
	FRENCH_SOUTHERN_TERRITORIES		("FRENCH SOUTHERN TERRITORIES",                     "TF",      "ATF",     260),
	GABON							("GABON",                                           "GA",      "GAB",     266),
	GAMBIA							("GAMBIA",                                          "GM",      "GMB",     270),
	GEORGIA							("GEORGIA",                                         "GE",      "GEO",     268),
	GERMANY							("GERMANY",                                         "DE",      "DEU",     276),
	GHANA							("GHANA",                                           "GH",      "GHA",     288),
	GIBRALTAR						("GIBRALTAR",                                       "GI",      "GIB",     292),
	GREECE							("GREECE",                                          "GR",      "GRC",     300),
	GREENLAND						("GREENLAND",                                       "GL",      "GRL",     304),
	GRENADA							("GRENADA",                                         "GD",      "GRD",     308),
	GUADELOUPE						("GUADELOUPE",                                      "GP",      "GLP",     312),
	GUAM							("GUAM",                                            "GU",      "GUM",     316),
	GUATEMALA						("GUATEMALA",                                       "GT",      "GTM",     320),
	GUINEA							("GUINEA",                                          "GN",      "GIN",     324),
	GUINEA_BISSAU					("GUINEA-BISSAU",                                   "GW",      "GNB",     624),
	GUYANA							("GUYANA",                                          "GY",      "GUY",     328),
	HAITI							("HAITI",                                           "HT",      "HTI",     332),
	HEARD_MC_DONALD_ISLANDS			("HEARD AND MC DONALD ISLANDS",                     "HM",      "HMD",     334),
	HONDURAS						("HONDURAS",                                        "HN",      "HND",     340),
	HONG_KONG						("HONG KONG",                                       "HK",      "HKG",     344),
	HUNGARY							("HUNGARY",                                         "HU",      "HUN",     348),
	ICELAND							("ICELAND",                                         "IS",      "ISL",     352),
	INDIA							("INDIA",                                           "IN",      "IND",     356),
	INDONESIA						("INDONESIA",                                       "ID",      "IDN",     360),
	IRAN							("IRAN (ISLAMIC REPUBLIC OF)",                      "IR",      "IRN",     364),
	IRAQ							("IRAQ",                                            "IQ",      "IRQ",     368),
	IRELAND							("IRELAND",                                         "IE",      "IRL",     372),
	ISRAEL							("ISRAEL",                                          "IL",      "ISR",     376),
	ITALY							("ITALY",                                           "IT",      "ITA",     380),
	JAMAICA							("JAMAICA",                                         "JM",      "JAM",     388),
	JAPAN							("JAPAN",                                           "JP",      "JPN",     392),
	JORDAN							("JORDAN",                                          "JO",      "JOR",     400),
	KAZAKHSTAN						("KAZAKHSTAN",                                      "KZ",      "KAZ",     398),
	KENYA							("KENYA",                                           "KE",      "KEN",     404),
	KIRIBATI						("KIRIBATI",                                        "KI",      "KIR",     296),
	KOREA_DEMOCRATIC				("KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF",          "KP",      "PRK",     408),
	KOREA_REPUBLIC					("KOREA, REPUBLIC OF",                              "KR",      "KOR",     410),
	KUWAIT							("KUWAIT",                                          "KW",      "KWT",     414),
	KYRGYZSTAN						("KYRGYZSTAN",                                      "KG",      "KGZ",     417),
	LAO								("LAO PEOPLE'S DEMOCRATIC REPUBLIC",                "LA",      "LAO",     418),
	LATVIA							("LATVIA",                                          "LV",      "LVA",     428),
	LEBANON							("LEBANON",                                         "LB",      "LBN",     422),
	LESOTHO							("LESOTHO",                                         "LS",      "LSO",     426),
	LIBERIA							("LIBERIA",                                         "LR",      "LBR",     430),
	LIBYAN							("LIBYAN ARAB JAMAHIRIYA",                          "LY",      "LBY",     434),
	LIECHTENSTEIN					("LIECHTENSTEIN",                                   "LI",      "LIE",     438),
	LITHUANIA						("LITHUANIA",                                       "LT",      "LTU",     440),
	LUXEMBOURG						("LUXEMBOURG",                                      "LU",      "LUX",     442),
	MACAU							("MACAU",                                           "MO",      "MAC",     446),
	MACEDONIA						("MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF",      "MK",      "MKD",     807),
	MADAGASCAR						("MADAGASCAR",                                      "MG",      "MDG",     450),
	MALAWI							("MALAWI",                                          "MW",      "MWI",     454),
	MALAYSIA						("MALAYSIA",                                        "MY",      "MYS",     458),
	MALDIVES						("MALDIVES",                                        "MV",      "MDV",     462),
	MALI							("MALI",                                            "ML",      "MLI",     466),
	MALTA							("MALTA",                                           "MT",      "MLT",     470),
	MARSHALL_ISLANDS				("MARSHALL ISLANDS",                                "MH",      "MHL",     584),
	MARTINIQUE						("MARTINIQUE",                                      "MQ",      "MTQ",     474),
	MAURITANIA						("MAURITANIA",                                      "MR",      "MRT",     478),
	MAURITIUS						("MAURITIUS",                                       "MU",      "MUS",     480),
	MAYOTTE							("MAYOTTE",                                         "YT",      "MYT",     175),
	MEXICO							("MEXICO",                                          "MX",      "MEX",     484),
	MICRONESIA						("MICRONESIA, FEDERATED STATES OF",                 "FM",      "FSM",     583),
	MOLDOVA							("MOLDOVA, REPUBLIC OF",                            "MD",      "MDA",     498),
	MONACO							("MONACO",                                          "MC",      "MCO",     492),
	MONGOLIA						("MONGOLIA",                                        "MN",      "MNG",     496),
	MONTSERRAT						("MONTSERRAT",                                      "MS",      "MSR",     500),
	MOROCCO							("MOROCCO",                                         "MA",      "MAR",     504),
	MOZAMBIQUE						("MOZAMBIQUE",                                      "MZ",      "MOZ",     508),
	MYANMAR							("MYANMAR",                                         "MM",      "MMR",     104),
	NAMIBIA							("NAMIBIA",                                         "NA",      "NAM",     516),
	NAURU							("NAURU",                                           "NR",      "NRU",     520),
	NEPAL							("NEPAL",                                           "NP",      "NPL",     524),
	NETHERLANDS						("NETHERLANDS",                                     "NL",      "NLD",     528),
	NETHERLANDS_ANTILLES			("NETHERLANDS ANTILLES",                            "AN",      "ANT",     530),
	NEW_CALEDONI					("NEW CALEDONIA",                                   "NC",      "NCL",     540),
	NEW_ZEALAND						("NEW ZEALAND",                                     "NZ",      "NZL",     554),
	NICARAGUA						("NICARAGUA",                                       "NI",      "NIC",     558),
	NIGER							("NIGER",                                           "NE",      "NER",     562),
	NIGERIA							("NIGERIA",                                         "NG",      "NGA",     566),
	NIUE							("NIUE",                                            "NU",      "NIU",     570),
	NORFOLK_ISLAND					("NORFOLK ISLAND",                                  "NF",      "NFK",     574),
	NORTHERN_MARIANA_ISLANDS		("NORTHERN MARIANA ISLANDS",                        "MP",      "MNP",     580),
	NORWAY							("NORWAY",                                          "NO",      "NOR",     578),
	OMAN							("OMAN",                                            "OM",      "OMN",     512),
	PAKISTAN						("PAKISTAN",                                        "PK",      "PAK",     586),
	PALAU							("PALAU",                                           "PW",      "PLW",     585),
	PALESTINIAN						("PALESTINIAN TERRITORY, Occupied",                 "PS",      "PSE",     275),
	PANAMA							("PANAMA",                                          "PA",      "PAN",     591),
	PAPUA_NEW_GUINEA				("PAPUA NEW GUINEA",                                "PG",      "PNG",     598),
	PARAGUAY						("PARAGUAY",                                        "PY",      "PRY",     600),
	PERU							("PERU",                                            "PE",      "PER",     604),
	PHILIPPINES						("PHILIPPINES",                                     "PH",      "PHL",     608),
	PITCAIRN						("PITCAIRN",                                        "PN",      "PCN",     612),
	POLAND							("POLAND",                                          "PL",      "POL",     616),
	PORTUGAL						("PORTUGAL",                                        "PT",      "PRT",     620),
	PUERTO_RICO						("PUERTO RICO",                                     "PR",      "PRI",     630),
	QATAR							("QATAR",                                           "QA",      "QAT",     634),
	REUNION							("REUNION",                                         "RE",      "REU",     638),
	ROMANIA							("ROMANIA",                                         "RO",      "ROU",     642),
	RUSSIAN_FEDERATION				("RUSSIAN FEDERATION",                              "RU",      "RUS",     643),
	RWANDA							("RWANDA",                                          "RW",      "RWA",     646),
	SAINT_HELENA					("SAINT HELENA",                                    "SH",      "SHN",     654),
	SAINT_KITTS_NEVIS				("SAINT KITTS AND NEVIS",                           "KN",      "KNA",     659),
	SAINT_LUCIA						("SAINT LUCIA",                                     "LC",      "LCA",     662),
	SAINT_PIERRE_MIQUELON			("SAINT PIERRE AND MIQUELON",                       "PM",      "SPM",     666),
	SAINT_VINCENT_GRENADINES		("SAINT VINCENT AND THE GRENADINES",                "VC",      "VCT",     670),
	SAMOA							("SAMOA",                                           "WS",      "WSM",     882),
	SAN_MARINO						("SAN MARINO",                                      "SM",      "SMR",     674),
	SAO_TOME_PRINCIPE				("SAO TOME AND PRINCIPE",                           "ST",      "STP",     678),
	SAUDI_ARABIA					("SAUDI ARABIA",                                    "SA",      "SAU",     682),
	SENEGAL							("SENEGAL",                                         "SN",      "SEN",     686),
	SERBIA_MONTENEGRO				("SERBIA AND MONTENEGRO",                           "CS",      "SCG",     891),
	SEYCHELLES						("SEYCHELLES",                                      "SC",      "SYC",     690),
	SIERRA_LEONE					("SIERRA LEONE",                                    "SL",      "SLE",     694),
	SINGAPORE						("SINGAPORE",                                       "SG",      "SGP",     702),
	SLOVAKIA						("SLOVAKIA",                                        "SK",      "SVK",     703),
	SLOVENIA						("SLOVENIA",                                        "SI",      "SVN",     705),
	SOLOMON_ISLAND					("SOLOMON ISLANDS",                                 "SB",      "SLB",     90),
	SOMALIA							("SOMALIA",                                         "SO",      "SOM",     706),
	SOUTH_AFRICA					("SOUTH AFRICA",                                    "ZA",      "ZAF",     710),
	SOUTH_GEORGIA					("SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS",    "GS",      "SGS",     239),
	SPAIN							("SPAIN",                                           "ES",      "ESP",     724),
	SRI_LANKA						("SRI LANKA",                                       "LK",      "LKA",     144),
	SUDAN							("SUDAN",                                           "SD",      "SDN",     736),
	SURINAME						("SURINAME",                                        "SR",      "SUR",     740),
	SVALBARD						("SVALBARD AND JAN MAYEN ISLANDS",                  "SJ",      "SJM",     744),
	SWAZILAND						("SWAZILAND",                                       "SZ",      "SWZ",     748),
	SWEDEN							("SWEDEN",                                          "SE",      "SWE",     752),
	SWITZERLAND						("SWITZERLAND",                                     "CH",      "CHE",     756),
	SYRIAN							("SYRIAN ARAB REPUBLIC",                            "SY",      "SYR",     760),
	TAIWAN							("TAIWAN",                                          "TW",      "TWN",     158),
	TAJIKISTAN						("TAJIKISTAN",                                      "TJ",      "TJK",     762),
	TANZANIA						("TANZANIA, UNITED REPUBLIC OF",                    "TZ",      "TZA",     834),
	THAILAND						("THAILAND",                                        "TH",      "THA",     764),
	TIMOR_LESTE						("TIMOR-LESTE",                                     "TL",      "TLS",     626),
	TOGO							("TOGO",                                            "TG",      "TGO",     768),
	TOKELAU							("TOKELAU",                                         "TK",      "TKL",     772),
	TONGA							("TONGA",                                           "TO",      "TON",     776),
	TRINIDAD_TOBAGO					("TRINIDAD AND TOBAGO",                             "TT",      "TTO",     780),
	TUNISIA							("TUNISIA",                                         "TN",      "TUN",     788),
	TURKEY							("TURKEY",                                          "TR",      "TUR",     792),
	TURKMENISTAN					("TURKMENISTAN",                                    "TM",      "TKM",     795),
	TURKS_CAICOS_ISLANDS			("TURKS AND CAICOS ISLANDS",                        "TC",      "TCA",     796),
	TUVALU							("TUVALU",                                          "TV",      "TUV",     798),
	UGANDA							("UGANDA",                                          "UG",      "UGA",     800),
	UKRAINE							("UKRAINE",                                         "UA",      "UKR",     804),
	UNITED_ARAB_EMIRATES			("UNITED ARAB EMIRATES",                            "AE",      "ARE",     784),
	UNITED_KINGDOM					("UNITED KINGDOM",                                  "GB",      "GBR",     826),
	UNITED_STATES					("UNITED STATES",                                   "US",      "USA",     840),
	UNITED_STATES_MINOR_ISLANDS		("UNITED STATES MINOR OUTLYING ISLANDS",            "UM",      "UMI",     581),
	URUGUAY							("URUGUAY",                                         "UY",      "URY",     858),
	UZBEKISTAN						("UZBEKISTAN",                                      "UZ",      "UZB",     860),
	VANUATU							("VANUATU",                                         "VU",      "VUT",     548),
	VATICAN_CITY_STATE				("VATICAN CITY STATE (HOLY SEE)",                   "VA",      "VAT",     336),
	VENEZUELA						("VENEZUELA",                                       "VE",      "VEN",     862),
	VIETNAM							("VIET NAM",                                        "VN",      "VNM",     704),
	VIRGIN_ISLANDS_BRITISH			("VIRGIN ISLANDS (BRITISH)",                        "VG",      "VGB",     92),
	VIRGIN_ISLANDS_USA				("VIRGIN ISLANDS (U.S.)",                           "VI",      "VIR",     850),
	WALLIS_FUTUNA_ISLANDS			("WALLIS AND FUTUNA ISLANDS",                       "WF",      "WLF",     876),
	WESTERN_SAHARA					("WESTERN SAHARA",                                  "EH",      "ESH",     732),
	YEMEN							("YEMEN",                                           "YE",      "YEM",     887),
	ZAMBIA							("ZAMBIA",                                          "ZM",      "ZMB",     894),
	ZIMBABWE						("ZIMBABWE",                                        "ZW",      "ZWE",     716);

	
	
	private static final Logger logger = Logger.getLogger(CountryEnum.class);

	private static Map<String, String> ISO2_To_Iso3_Map;
	private static Map<String, String> ISO3_To_Iso2_Map;
	private static Map<Integer, String> ID_To_Iso2_Map;
	private static Map<String, Locale> ISO3_To_Locale;
	private static boolean IS_INITIALIZED;
	private String displayName;
	private String iso2Code;
	private String iso3Code;
	private int id;
	
	CountryEnum(String displayName, String iso2Code, String iso3Code, int id){
		if(!isInitialized()){
			init();
		}
		setDisplayName(displayName);
		setIso2Code(iso2Code);
		setIso3Code(iso3Code);
		setId(id);
		
		mapISOCodes(iso2Code, iso3Code, id);
	}
	
	private synchronized boolean isInitialized(){
		return IS_INITIALIZED;
	}
	
	private synchronized void init(){
		ISO2_To_Iso3_Map = new HashMap<String, String>();
		ISO3_To_Iso2_Map = new HashMap<String, String>();
		ID_To_Iso2_Map = new HashMap<Integer, String>();
		ISO3_To_Locale = new HashMap<String, Locale>();
		
		Locale[] availableLocales = Locale.getAvailableLocales();
		for ( Locale locale : availableLocales ) {
			try{
				ISO3_To_Locale.put(locale.getISO3Country(), locale);
			}catch(Exception ex){
				System.err.println("CountryEnum init exception : "+ ex.getMessage());
			}
        }
		
		IS_INITIALIZED = true;
	}
	
	private void mapISOCodes(String iso2Code, String iso3Code, int id){
		ISO2_To_Iso3_Map.put(iso2Code, iso3Code);
		ISO3_To_Iso2_Map.put(iso3Code, iso2Code);
		ID_To_Iso2_Map.put(new Integer(id), iso2Code);
	}
	
	public static String getISO3CodeFromISO2Code(String iso2Code){
		if(iso2Code == null) {
			return iso2Code;
		}
		return ISO3_To_Iso2_Map.get(iso2Code);
	}
	
	public static String getISO2CodeFromISO3Code(String iso3Code){
		if(iso3Code == null) {
			return iso3Code;
		}
		return ISO3_To_Iso2_Map.get(iso3Code);
	}
	
	public static String getISO2Code(int id){
		return ID_To_Iso2_Map.get(new Integer(id));
	}
	
	public static Locale getCountryLocale(String iso3Code) {
		return ISO3_To_Locale.get(iso3Code);
	}
	
	public static CountryEnum getCountryFromISO3Code(String iso3Code) {
		if((iso3Code == null) || (iso3Code.isEmpty())) {
			return null;
		}
		
		CountryEnum[] countries = CountryEnum.values();
		for(CountryEnum country : countries) {
			if(country.getIso3Code().equals(iso3Code)) {
				return country;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIso2Code() {
		return iso2Code;
	}
	public void setIso2Code(String iso2Code) {
		this.iso2Code = iso2Code;
	}
	public String getIso3Code() {
		return iso3Code;
	}
	public void setIso3Code(String iso3Code) {
		this.iso3Code = iso3Code;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public Locale getLocale() {
		return getCountryLocale(getIso3Code());
	}
}
