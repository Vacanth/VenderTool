package com.vendertool.sharedtypes.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Language {
	private String isoLangCode;
	private String englishName;
	private String nativeName;
	
	@JsonIgnore
	private static Map<String, Language> langCode2LangMap = new HashMap<String, Language>();
	
	private Language(String isoLangCode, 
			String englishName, String nativeName) {
		this.isoLangCode = isoLangCode;
		this.englishName = englishName;
		this.nativeName = nativeName;
		langCode2LangMap.put(this.isoLangCode, this);
	}
	
	public String getIsoLangCode() {
		return isoLangCode;
	}
	
	public String getEnglishName() {
		return englishName;
	}
	
	public String getNativeName() {
		return nativeName;
	}
	
	public static Language getLanguage(String isoLangCode) {
		return langCode2LangMap.get(isoLangCode);
	}
	
	static {
	    new Language("ab", "Abkhaz", "аҧсуа");
	    new Language("aa", "Afar", "Afaraf");
	    new Language("af", "Afrikaans", "Afrikaans");
	    new Language("ak", "Akan", "Akan");
	    new Language("sq", "Albanian", "Shqip");
	    new Language("am", "Amharic", "አማርኛ");
	    new Language("ar", "Arabic", "العربية");
	    new Language("an", "Aragonese", "Aragonés");
	    new Language("hy", "Armenian", "Հայերեն");
	    new Language("as", "Assamese", "অসমীয়া");
	    new Language("av", "Avaric", "авар мацӀ, магӀарул мацӀ");
	    new Language("ae", "Avestan", "avesta");
	    new Language("ay", "Aymara", "aymar aru");
	    new Language("az", "Azerbaijani", "azərbaycan dili");
	    new Language("bm", "Bambara", "bamanankan");
	    new Language("ba", "Bashkir", "башҡорт теле");
	    new Language("eu", "Basque", "euskara, euskera");
	    new Language("be", "Belarusian", "Беларуская");
	    new Language("bn", "Bengali", "বাংলা");
	    new Language("bh", "Bihari", "भोजपुरी");
	    new Language("bi", "Bislama", "Bislama");
	    new Language("bs", "Bosnian", "bosanski jezik");
	    new Language("br", "Breton", "brezhoneg");
	    new Language("bg", "Bulgarian", "български език");
	    new Language("my", "Burmese", "ဗမာစာ");
	    new Language("ca", "Catalan; Valencian", "Català");
	    new Language("ch", "Chamorro", "Chamoru");
	    new Language("ce", "Chechen", "нохчийн мотт");
	    new Language("ny", "Chichewa; Chewa; Nyanja", "chiCheŵa, chinyanja");
	    new Language("zh", "Chinese", "中文 (Zhōngwén), 汉语, 漢語");
	    new Language("cv", "Chuvash", "чӑваш чӗлхи");
	    new Language("kw", "Cornish", "Kernewek");
	    new Language("co", "Corsican", "corsu, lingua corsa");
	    new Language("cr", "Cree", "ᓀᐦᐃᔭᐍᐏᐣ");
	    new Language("hr", "Croatian", "hrvatski");
	    new Language("cs", "Czech", "česky, čeština");
	    new Language("da", "Danish", "dansk");
	    new Language("dv", "Divehi; Dhivehi; Maldivian;", "ދިވެހި");
	    new Language("nl", "Dutch", "Nederlands, Vlaams");
	    new Language("en", "English", "English");
	    new Language("eo", "Esperanto", "Esperanto");
	    new Language("et", "Estonian", "eesti, eesti keel");
	    new Language("ee", "Ewe", "Eʋegbe");
	    new Language("fo", "Faroese", "føroyskt");
	    new Language("fj", "Fijian", "vosa Vakaviti");
	    new Language("fi", "Finnish", "suomi, suomen kieli");
	    new Language("fr", "French", "français, langue française");
	    new Language("ff", "Fula; Fulah; Pulaar; Pular", "Fulfulde, Pulaar, Pular");
	    new Language("gl", "Galician", "Galego");
	    new Language("ka", "Georgian", "ქართული");
	    new Language("de", "German", "Deutsch");
	    new Language("el", "Greek, Modern", "Ελληνικά");
	    new Language("gn", "Guaraní", "Avañeẽ");
	    new Language("gu", "Gujarati", "ગુજરાતી");
	    new Language("ht", "Haitian; Haitian Creole", "Kreyòl ayisyen");
	    new Language("ha", "Hausa", "Hausa, هَوُسَ");
	    new Language("he", "Hebrew (modern)", "עברית");
	    new Language("hz", "Herero", "Otjiherero");
	    new Language("hi", "Hindi", "हिन्दी, हिंदी");
	    new Language("ho", "Hiri Motu", "Hiri Motu");
	    new Language("hu", "Hungarian", "Magyar");
	    new Language("ia", "Interlingua", "Interlingua");
	    new Language("id", "Indonesian", "Bahasa Indonesia");
	    new Language("ie", "Interlingue", "Originally called Occidental; then Interlingue after WWII");
	    new Language("ga", "Irish", "Gaeilge");
	    new Language("ig", "Igbo", "Asụsụ Igbo");
	    new Language("ik", "Inupiaq", "Iñupiaq, Iñupiatun");
	    new Language("io", "Ido", "Ido");
	    new Language("is", "Icelandic", "Íslenska");
	    new Language("it", "Italian", "Italiano");
	    new Language("iu", "Inuktitut", "ᐃᓄᒃᑎᑐᑦ");
	    new Language("ja", "Japanese", "日本語 (にほんご／にっぽんご)");
	    new Language("jv", "Javanese", "basa Jawa");
	    new Language("kl", "Kalaallisut, Greenlandic", "kalaallisut, kalaallit oqaasii");
	    new Language("kn", "Kannada", "ಕನ್ನಡ");
	    new Language("kr", "Kanuri", "Kanuri");
	    new Language("ks", "Kashmiri", "कश्मीरी, كشميري‎");
	    new Language("kk", "Kazakh", "Қазақ тілі");
	    new Language("km", "Khmer", "ភាសាខ្មែរ");
	    new Language("ki", "Kikuyu, Gikuyu", "Gĩkũyũ");
	    new Language("rw", "Kinyarwanda", "Ikinyarwanda");
	    new Language("ky", "Kirghiz, Kyrgyz", "кыргыз тили");
	    new Language("kv", "Komi", "коми кыв");
	    new Language("kg", "Kongo", "KiKongo");
	    new Language("ko", "Korean", "한국어 (韓國語), 조선말 (朝鮮語)");
	    new Language("ku", "Kurdish", "Kurdî, كوردی‎");
	    new Language("kj", "Kwanyama, Kuanyama", "Kuanyama");
	    new Language("la", "Latin", "latine, lingua latina");
	    new Language("lb", "Luxembourgish, Letzeburgesch", "Lëtzebuergesch");
	    new Language("lg", "Luganda", "Luganda");
	    new Language("li", "Limburgish, Limburgan, Limburger", "Limburgs");
	    new Language("ln", "Lingala", "Lingála");
	    new Language("lo", "Lao", "ພາສາລາວ");
	    new Language("lt", "Lithuanian", "lietuvių kalba");
	    new Language("lu", "Luba-Katanga", "");
	    new Language("lv", "Latvian", "latviešu valoda");
	    new Language("gv", "Manx", "Gaelg, Gailck");
	    new Language("mk", "Macedonian", "македонски јазик");
	    new Language("mg", "Malagasy", "Malagasy fiteny");
	    new Language("ms", "Malay", "bahasa Melayu, بهاس ملايو‎");
	    new Language("ml", "Malayalam", "മലയാളം");
	    new Language("mt", "Maltese", "Malti");
	    new Language("mi", "Māori", "te reo Māori");
	    new Language("mr", "Marathi (Marāṭhī)", "मराठी");
	    new Language("mh", "Marshallese", "Kajin M̧ajeļ");
	    new Language("mn", "Mongolian", "монгол");
	    new Language("na", "Nauru", "Ekakairũ Naoero");
	    new Language("nv", "Navajo, Navaho", "Diné bizaad, Dinékʼehǰí");
	    new Language("nb", "Norwegian Bokmål", "Norsk bokmål");
	    new Language("nd", "North Ndebele", "isiNdebele");
	    new Language("ne", "Nepali", "नेपाली");
	    new Language("ng", "Ndonga", "Owambo");
	    new Language("nn", "Norwegian Nynorsk", "Norsk nynorsk");
	    new Language("no", "Norwegian", "Norsk");
	    new Language("ii", "Nuosu", "ꆈꌠ꒿ Nuosuhxop");
	    new Language("nr", "South Ndebele", "isiNdebele");
	    new Language("oc", "Occitan", "Occitan");
	    new Language("oj", "Ojibwe, Ojibwa", "ᐊᓂᔑᓈᐯᒧᐎᓐ");
	    new Language("cu", "Old Church Slavonic, Church Slavic, Church Slavonic, Old Bulgarian, Old Slavonic", "ѩзыкъ словѣньскъ");
	    new Language("om", "Oromo", "Afaan Oromoo");
	    new Language("or", "Oriya", "ଓଡ଼ିଆ");
	    new Language("os", "Ossetian, Ossetic", "ирон æвзаг");
	    new Language("pa", "Panjabi, Punjabi", "ਪੰਜਾਬੀ, پنجابی‎");
	    new Language("pi", "Pāli", "पाऴि");
	    new Language("fa", "Persian", "فارسی");
	    new Language("pl", "Polish", "polski");
	    new Language("ps", "Pashto, Pushto", "پښتو");
	    new Language("pt", "Portuguese", "Português");
	    new Language("qu", "Quechua", "Runa Simi, Kichwa");
	    new Language("rm", "Romansh", "rumantsch grischun");
	    new Language("rn", "Kirundi", "kiRundi");
	    new Language("ro", "Romanian, Moldavian, Moldovan", "română");
	    new Language("ru", "Russian", "русский язык");
	    new Language("sa", "Sanskrit (Saṁskṛta)", "संस्कृतम्");
	    new Language("sc", "Sardinian", "sardu");
	    new Language("sd", "Sindhi", "सिन्धी, سنڌي، سندھی‎");
	    new Language("se", "Northern Sami", "Davvisámegiella");
	    new Language("sm", "Samoan", "gagana faa Samoa");
	    new Language("sg", "Sango", "yângâ tî sängö");
	    new Language("sr", "Serbian", "српски језик");
	    new Language("gd", "Scottish Gaelic; Gaelic", "Gàidhlig");
	    new Language("sn", "Shona", "chiShona");
	    new Language("si", "Sinhala, Sinhalese", "සිංහල");
	    new Language("sk", "Slovak", "slovenčina");
	    new Language("sl", "Slovene", "slovenščina");
	    new Language("so", "Somali", "Soomaaliga, af Soomaali");
	    new Language("st", "Southern Sotho", "Sesotho");
	    new Language("es", "Spanish; Castilian", "español, castellano");
	    new Language("su", "Sundanese", "Basa Sunda");
	    new Language("sw", "Swahili", "Kiswahili");
	    new Language("ss", "Swati", "SiSwati");
	    new Language("sv", "Swedish", "svenska");
	    new Language("ta", "Tamil", "தமிழ்");
	    new Language("te", "Telugu", "తెలుగు");
	    new Language("tg", "Tajik", "тоҷикӣ, toğikī, تاجیکی‎");
	    new Language("th", "Thai", "ไทย");
	    new Language("ti", "Tigrinya", "ትግርኛ");
	    new Language("bo", "Tibetan Standard, Tibetan, Central", "བོད་ཡིག");
	    new Language("tk", "Turkmen", "Türkmen, Түркмен");
	    new Language("tl", "Tagalog", "Wikang Tagalog, ᜏᜒᜃᜅ᜔ ᜆᜄᜎᜓᜄ᜔");
	    new Language("tn", "Tswana", "Setswana");
	    new Language("to", "Tonga (Tonga Islands)", "faka Tonga");
	    new Language("tr", "Turkish", "Türkçe");
	    new Language("ts", "Tsonga", "Xitsonga");
	    new Language("tt", "Tatar", "татарча, tatarça, تاتارچا‎");
	    new Language("tw", "Twi", "Twi");
	    new Language("ty", "Tahitian", "Reo Tahiti");
	    new Language("ug", "Uighur, Uyghur", "Uyƣurqə, ئۇيغۇرچە‎");
	    new Language("uk", "Ukrainian", "українська");
	    new Language("ur", "Urdu", "اردو");
	    new Language("uz", "Uzbek", "zbek, Ўзбек, أۇزبېك‎");
	    new Language("ve", "Venda", "Tshivenḓa");
	    new Language("vi", "Vietnamese", "Tiếng Việt");
	    new Language("vo", "Volapük", "Volapük");
	    new Language("wa", "Walloon", "Walon");
	    new Language("cy", "Welsh", "Cymraeg");
	    new Language("wo", "Wolof", "Wollof");
	    new Language("fy", "Western Frisian", "Frysk");
	    new Language("xh", "Xhosa", "isiXhosa");
	    new Language("yi", "Yiddish", "ייִדיש");
	    new Language("yo", "Yoruba", "Yorùbá");
	    new Language("za", "Zhuang, Chuang", "Saɯ cueŋƅ, Saw cuengh");
	}
	
	@JsonIgnore
	public static Set<Language> getLanguages() {
		Set<Language> languages = new HashSet<Language>();
		languages.addAll(langCode2LangMap.values());
		return languages;
	}
}
