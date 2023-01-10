package geo;

public final class Country {
    private IcaoCode icaoCode;
    private String countryName;
    private String officialStateName;
    private String alpha3;
    private String alpha2;
    private String tld;
    private Area area;
    private String sovereignty = "tba";
    private String currency = "tba";
    private double exchangeRate;

    public Country(IcaoCode icaoCode) {
        this.icaoCode = icaoCode;
        this.area = switch (this.icaoCode) {
            default -> null; // just in case as there might be weird ctry codes that likely were made up for an aside project. to be xchecked later
            case BG, BI, BK, EB, ED, EF, EG, EH, EI, EK, EL, EN, ES, LA, LD, LE, LF, LG, LI, LJ, LM, LO, LP, LQ, LS, LW, LX, LY ->
                    Area.EUR;
            case EE, EP, EV, EY, LB, LH, LK, LR, LU, LZ, UA, UB, UC, UD, UE, UG, UH, UI, UK, UL, UM, UN, UO, UR, US, UT, UU, UW, ZB, ZG, ZH, ZJ, ZK, ZL, ZM, ZP, ZS, ZU, ZW, ZY ->
                    Area.EEU;
            case KZ, K1, K2, K3, K4, K5, K6, K7, MB, MY, PA, PC, PG, PH, PK, PL, PM, PP, PT, PW, TX -> Area.USA;
            case CY, CZ -> Area.CAN;
            case AG, AN, AY, NC, NF, NG, NI, NL, NS, NT, NV, NW, NZ, WA, WB, WI, WM, WP, WS, YA, YB, YC, YD, YE, YF, YG, YH, YI, YJ, YK, YL, YM, YN, YO, YP, YQ, YR, YS, YT, YW ->
                    Area.SPA;
            case DA, DB, DF, DG, DI, DN, DR, DT, DX, FA, FB, FC, FD, FE, FG, FI, FJ, FK, FL, FM, FN, FO, FP, FQ, FS, FT, FV, FW, FX, FY, FZ, GA, GB, GC, GE, GF, GG, GL, GM, GO, GQ, GU, GV, HA, HB, HC, HD, HE, HH, HJ, HK, HL, HR, HS, HT, HU ->
                    Area.AFR;
            case LC, LL, LT, OA, OB, OE, OI, OJ, OK, OL, OM, OO, OP, OR, OS, OT, OY, VA, VC, VE, VG, VI, VN, VO, VQ, VR ->
                    Area.MES;
            case MD, MG, MH, MK, MM, MN, MP, MR, MS, MT, MU, MW, MZ, TA, TB, TD, TF, TG, TI, TJ, TK, TL, TN, TQ, TT, TU, TV ->
                    Area.LAM;
            case RC, RJ, RK, RO, RP, VD, VH, VL, VM, VT, VV, VY -> Area.PAC;
            case FH, SA, SB, SC, SD, SE, SF, SG, SI, SJ, SK, SL, SM, SN, SO, SP, SS, SU, SV, SW, SY -> Area.SAM;
        };

        this.countryName = icaoCode.getSimpleCountryName();
        this.alpha2 = icaoCode.getAlpha2();
        this.alpha3 = icaoCode.getAlpha3();
        this.tld = icaoCode.getTld();
        this.officialStateName = icaoCode.getOfficialStateName();
    }



    public IcaoCode getIcaoCode() {
        return icaoCode;
    }

    public String getOfficialStateName() {
        return officialStateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public Area getArea() {
        return area;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }

    public String getTld() {
        return tld;
    }

    @Override
    public String toString() {
        return "Country{\n" +
                "icaoCode=" + icaoCode +
                ",\ncountryName='" + countryName + '\'' +
                ",\nofficialStateName='" + officialStateName + '\'' +
                ",\nalpha3='" + alpha3 + '\'' +
                ",\nalpha2='" + alpha2 + '\'' +
                ",\ntld='" + tld + '\'' +
                ",\narea=" + area +
                ",\nsovereignty='" + sovereignty + '\'' +
                ",\ncurrency='" + currency + '\'' +
                ",\nexchangeRate=" + exchangeRate +
                '}';
    }
    public enum IcaoCode {
        AG("SOLOMON ISLANDS", "The Solomon Islands", "SB", "SLB", ".sb"), AN("PAPUA NEW GUINEA", "The Independent State of Papua New Guinea", "PG", "PNG", ".pg"), AY("PAPUA NEW GUINEA", "The Independent State of Papua New Guinea", "PG", "PNG", ".pg"), BG("GREENLAND", "Kalaallit Nunaat", "GL", "GRL", ".gl"), BI("ICELAND", "Iceland", "IS", "ISL", ".is"), BK("SERBIA AND MONTENEGR", "#N/A", "#N/A", "#N/A", "#N/A"), CY("CANADA", "Canada", "CA", "CAN", ".ca"), CZ("CANADA", "Canada", "CA", "CAN", ".ca"), DA("ALGERIA", "The People's Democratic Republic of Algeria", "DZ", "DZA", ".dz"), DB("BENIN", "The Republic of Benin", "BJ", "BEN", ".bj"), DF("BURKINA FASO", "Burkina Faso", "BF", "BFA", ".bf"), DG("GHANA", "The Republic of Ghana", "GH", "GHA", ".gh"), DI("COTE D'IVOIRE", "The Republic of Côte d'Ivoire", "CI", "CIV", ".ci"), DN("NIGERIA", "The Federal Republic of Nigeria", "NG", "NGA", ".ng"), DR("NIGER", "The Republic of the Niger", "NE", "NER", ".ne"), DT("TUNISIA", "The Republic of Tunisia", "TN", "TUN", ".tn"), DX("TOGO", "The Togolese Republic", "TG", "TGO", ".tg"), EB("BELGIUM", "The Kingdom of Belgium", "BE", "BEL", ".be"), ED("GERMANY", "The Federal Republic of Germany", "DE", "DEU", ".de"), EE("ESTONIA", "The Republic of Estonia", "EE", "EST", ".ee"), EF("FINLAND", "The Republic of Finland", "FI", "FIN", ".fi"), EG("UNITED KINGDOM", "The United Kingdom of Great Britain and Northern Ireland", "GB", "GBR", ".gb .uk [ac]"), EH("NETHERLANDS", "The Kingdom of the Netherlands", "NL", "NLD", ".nl"), EI("IRELAND", "Ireland", "IE", "IRL", ".ie"), EK("DENMARK", "The Kingdom of Denmark", "DK", "DNK", ".dk"), EL("LUXEMBOURG", "The Grand Duchy of Luxembourg", "LU", "LUX", ".lu"), EN("NORWAY", "The Kingdom of Norway", "NO", "NOR", ".no"), EP("POLAND", "The Republic of Poland", "PL", "POL", ".pl"), EQ("AFGHANISTAN", "The Islamic Republic of Afghanistan", "AF", "AFG", ".af"), ES("SWEDEN", "The Kingdom of Sweden", "SE", "SWE", ".se"), ET("GERMANY", "The Federal Republic of Germany", "DE", "DEU", ".de"), EV("LATVIA", "The Republic of Latvia", "LV", "LVA", ".lv"), EY("LITHUANIA", "The Republic of Lithuania", "LT", "LTU", ".lt"), FA("SOUTH AFRICA", "The Republic of South Africa", "ZA", "ZAF", ".za"), FB("BOTSWANA", "The Republic of Botswana", "BW", "BWA", ".bw"), FC("CONGO", "The Republic of the Congo", "CG", "COG", ".cg"), FD("SOUTH AFRICA", "The Republic of South Africa", "ZA", "ZAF", ".za"), FE("CENTRAL AFRICAN REPU", "#N/A", "#N/A", "#N/A", "#N/A"), FG("EQUATORIAL GUINEA", "The Republic of Equatorial Guinea", "GQ", "GNQ", ".gq"), FH("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), FI("MAURITIUS", "The Republic of Mauritius", "MU", "MUS", ".mu"), FJ("BRITISH INDIAN OCEAN", "#N/A", "#N/A", "#N/A", "#N/A"), FK("CHAD", "The Republic of Chad", "TD", "TCD", ".td"), FL("ZAMBIA", "The Republic of Zambia", "ZM", "ZMB", ".zm"), FM("MADAGASCAR", "The Republic of Madagascar", "MG", "MDG", ".mg"), FN("ANGOLA", "The Republic of Angola", "AO", "AGO", ".ao"), FO("CONGO", "The Republic of the Congo", "CG", "COG", ".cg"), FP("SAO TOME AND PRINCIP", "#N/A", "#N/A", "#N/A", "#N/A"), FQ("MOZAMBIQUE", "The Republic of Mozambique", "MZ", "MOZ", ".mz"), FS("SEYCHELLES", "The Republic of Seychelles", "SC", "SYC", ".sc"), FT("CHAD", "The Republic of Chad", "TD", "TCD", ".td"), FV("ZIMBABWE", "The Republic of Zimbabwe", "ZW", "ZWE", ".zw"), FW("MALAWI", "The Republic of Malawi", "MW", "MWI", ".mw"), FX("SOUTH AFRICA", "The Republic of South Africa", "ZA", "ZAF", ".za"), FY("NAMIBIA", "The Republic of Namibia", "NA", "NAM", ".na"), FZ("DEM REP OF CONGO", "The Democratic Republic of the Congo", "CD", "COD", ".cd"), GA("MALI", "The Republic of Mali", "ML", "MLI", ".ml"), GB("GAMBIA", "The Republic of The Gambia", "GM", "GMB", ".gm"), GC("SPAIN", "The Kingdom of Spain", "ES", "ESP", ".es"), GE("SPAIN", "The Kingdom of Spain", "ES", "ESP", ".es"), GF("SIERRA LEONE", "The Republic of Sierra Leone", "SL", "SLE", ".sl"), GG("GUINEA-BISSAU", "The Republic of Guinea-Bissau", "GW", "GNB", ".gw"), GL("LIBERIA", "The Republic of Liberia", "LR", "LBR", ".lr"), GM("MOROCCO", "The Kingdom of Morocco", "MA", "MAR", ".ma"), GO("SENEGAL", "The Republic of Senegal", "SN", "SEN", ".sn"), GQ("MAURITANIA", "The Islamic Republic of Mauritania", "MR", "MRT", ".mr"), GU("GUINEA", "The Republic of Guinea", "GN", "GIN", ".gn"), GV("CAPE VERDE", "The Republic of Cabo Verde", "CV", "CPV", ".cv"), HA("ETHIOPIA", "The Federal Democratic Republic of Ethiopia", "ET", "ETH", ".et"), HB("BURUNDI", "The Republic of Burundi", "BI", "BDI", ".bi"), HC("SOMALIA", "The Federal Republic of Somalia", "SO", "SOM", ".so"), HD("DJIBOUTI", "The Republic of Djibouti", "DJ", "DJI", ".dj"), HE("EGYPT", "The Arab Republic of Egypt", "EG", "EGY", ".eg"), HH("ERITREA", "The State of Eritrea", "ER", "ERI", ".er"), HJ("SOUTH SUDAN", "The Republic of South Sudan", "SS", "SSD", ".ss"), HK("KENYA", "The Republic of Kenya", "KE", "KEN", ".ke"), HL("LIBYA", "The State of Libya", "LY", "LBY", ".ly"), HR("RWANDA", "The Republic of Rwanda", "RW", "RWA", ".rw"), HS("SUDAN", "The Republic of the Sudan", "SD", "SDN", ".sd"), HT("TANZANIA UNITED REPU", "#N/A", "#N/A", "#N/A", "#N/A"), HU("UGANDA", "The Republic of Uganda", "UG", "UGA", ".ug"), K1("UNITED STATES", "The United States of America", "US", "USA", ".us"), K2("UNITED STATES", "The United States of America", "US", "USA", ".us"), K3("UNITED STATES", "The United States of America", "US", "USA", ".us"), K4("UNITED STATES", "The United States of America", "US", "USA", ".us"), K5("UNITED STATES", "The United States of America", "US", "USA", ".us"), K6("UNITED STATES", "The United States of America", "US", "USA", ".us"), K7("UNITED STATES", "The United States of America", "US", "USA", ".us"), KZ("UNITED STATES", "The United States of America", "US", "USA", ".us"), LA("ALBANIA", "The Republic of Albania", "AL", "ALB", ".al"), LB("BULGARIA", "The Republic of Bulgaria", "BG", "BGR", ".bg"), LC("TURKEY", "The Republic of Türkiye", "TR", "TUR", ".tr"), LD("CROATIA", "The Republic of Croatia", "HR", "HRV", ".hr"), LE("SPAIN", "The Kingdom of Spain", "ES", "ESP", ".es"), LF("FRANCE", "The French Republic", "FR", "FRA", ".fr"), LG("GREECE", "The Hellenic Republic", "GR", "GRC", ".gr"), LH("HUNGARY", "Hungary", "HU", "HUN", ".hu"), LI("ITALY", "The Italian Republic", "IT", "ITA", ".it"), LJ("SLOVENIA", "The Republic of Slovenia", "SI", "SVN", ".si"), LK("CZECH REPUBLIC", "The Czech Republic", "CZ", "CZE", ".cz"), LL("ISRAEL", "The State of Israel", "IL", "ISR", ".il"), LM("MALTA", "The Republic of Malta", "MT", "MLT", ".mt"), LO("AUSTRIA", "The Republic of Austria", "AT", "AUT", ".at"), LP("PORTUGAL", "The Portuguese Republic", "PT", "PRT", ".pt"), LQ("BOSNIA AND HERZEGOWI", "#N/A", "#N/A", "#N/A", "#N/A"), LR("ROMANIA", "Romania", "RO", "ROU", ".ro"), LS("SWITZERLAND", "The Swiss Confederation", "CH", "CHE", ".ch"), LT("TURKEY", "The Republic of Türkiye", "TR", "TUR", ".tr"), LU("MOLDOVA REPUBLIC OF", "The Republic of Moldova", "MD", "MDA", ".md"), LV("ISRAEL", "The State of Israel", "IL", "ISR", ".il"), LW("NORTH MACEDONIA", "The Republic of North Macedonia[12]", "MK", "MKD", ".mk"), LX("GIBRALTAR", "Gibraltar", "GI", "GIB", ".gi"), LY("SERBIA AND MONTENEGR", "#N/A", "#N/A", "#N/A", "#N/A"), LZ("SLOVAKIA (SLOVAK REP", "#N/A", "#N/A", "#N/A", "#N/A"), MB("TURKS AND CAICOS ISL", "#N/A", "#N/A", "#N/A", "#N/A"), MD("DOMINICAN REPUBLIC", "The Dominican Republic", "DO", "DOM", ".do"), MG("GUATEMALA", "The Republic of Guatemala", "GT", "GTM", ".gt"), MH("HONDURAS", "The Republic of Honduras", "HN", "HND", ".hn"), MK("JAMAICA", "Jamaica", "JM", "JAM", ".jm"), MM("MEXICO", "The United Mexican States", "MX", "MEX", ".mx"), MN("HONDURAS", "The Republic of Honduras", "HN", "HND", ".hn"), MP("PANAMA", "The Republic of Panamá", "PA", "PAN", ".pa"), MR("COSTA RICA", "The Republic of Costa Rica", "CR", "CRI", ".cr"), MS("EL SALVADOR", "The Republic of El Salvador", "SV", "SLV", ".sv"), MT("HAITI", "The Republic of Haiti", "HT", "HTI", ".ht"), MU("CUBA", "The Republic of Cuba", "CU", "CUB", ".cu"), MW("CAYMAN ISLANDS", "The Cayman Islands", "KY", "CYM", ".ky"), MY("BAHAMAS", "The Commonwealth of The Bahamas", "BS", "BHS", ".bs"), MZ("BELIZE", "Belize", "BZ", "BLZ", ".bz"), NC("COOK ISLANDS", "The Cook Islands", "CK", "COK", ".ck"), NF("FIJI", "The Republic of Fiji", "FJ", "FJI", ".fj"), NG("FIJI", "The Republic of Fiji", "FJ", "FJI", ".fj"), NI("NEW ZEALAND", "New Zealand", "NZ", "NZL", ".nz"), NL("WALLIS AND FUTUNA IS", "#N/A", "#N/A", "#N/A", "#N/A"), NO("SOUTH AFRICA", "The Republic of South Africa", "ZA", "ZAF", ".za"), NS("NEW ZEALAND", "New Zealand", "NZ", "NZL", ".nz"), NT("FRENCH POLYNESIA", "French Polynesia", "PF", "PYF", ".pf"), NV("VANUATU", "The Republic of Vanuatu", "VU", "VUT", ".vu"), NW("NEW CALEDONIA", "New Caledonia", "NC", "NCL", ".nc"), NZ("NEW ZEALAND", "New Zealand", "NZ", "NZL", ".nz"), OA("AFGHANISTAN", "The Islamic Republic of Afghanistan", "AF", "AFG", ".af"), OB("BAHRAIN", "The Kingdom of Bahrain", "BH", "BHR", ".bh"), OE("SAUDI ARABIA", "The Kingdom of Saudi Arabia", "SA", "SAU", ".sa"), OI("IRAN", "The Islamic Republic of Iran", "IR", "IRN", ".ir"), OJ("JORDAN", "The Hashemite Kingdom of Jordan", "JO", "JOR", ".jo"), OK("KUWAIT", "The State of Kuwait", "KW", "KWT", ".kw"), OL("LEBANON", "The Lebanese Republic", "LB", "LBN", ".lb"), OM("UNITED ARAB EMIRATES", "The United Arab Emirates", "AE", "ARE", ".ae"), OO("OMAN", "The Sultanate of Oman", "OM", "OMN", ".om"), OP("PAKISTAN", "The Islamic Republic of Pakistan", "PK", "PAK", ".pk"), OR("IRAQ", "The Republic of Iraq", "IQ", "IRQ", ".iq"), OS("SYRIAN ARAB REPUBLIC", "The Syrian Arab Republic", "SY", "SYR", ".sy"), OT("QATAR", "The State of Qatar", "QA", "QAT", ".qa"), OY("YEMEN", "The Republic of Yemen", "YE", "YEM", ".ye"), PA("UNITED STATES", "The United States of America", "US", "USA", ".us"), PC("UNITED STATES", "The United States of America", "US", "USA", ".us"), PG("UNITED STATES", "The United States of America", "US", "USA", ".us"), PH("UNITED STATES", "The United States of America", "US", "USA", ".us"), PK("MARSHALL ISLANDS", "The Republic of the Marshall Islands", "MH", "MHL", ".mh"), PL("KIRIBATI", "The Republic of Kiribati", "KI", "KIR", ".ki"), PM("UNITED STATES", "The United States of America", "US", "USA", ".us"), PO("GREENLAND", "Kalaallit Nunaat", "GL", "GRL", ".gl"), PP("UNITED STATES", "The United States of America", "US", "USA", ".us"), PT("UNITED STATES", "The United States of America", "US", "USA", ".us"), PW("UNITED STATES", "The United States of America", "US", "USA", ".us"), RC("TAIWAN PROVINCE OF C", "#N/A", "#N/A", "#N/A", "#N/A"), RJ("JAPAN", "Japan", "JP", "JPN", ".jp"), RK("KOREA REPUBLIC OF", "The Republic of Korea", "KR", "KOR", ".kr"), RO("JAPAN", "Japan", "JP", "JPN", ".jp"), RP("PHILIPPINES", "The Republic of the Philippines", "PH", "PHL", ".ph"), SA("ARGENTINA", "The Argentine Republic", "AR", "ARG", ".ar"), SB("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SC("CHILE", "The Republic of Chile", "CL", "CHL", ".cl"), SD("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SE("ECUADOR", "The Republic of Ecuador", "EC", "ECU", ".ec"), SF("ARGENTINA", "The Argentine Republic", "AR", "ARG", ".ar"), SG("PARAGUAY", "The Republic of Paraguay", "PY", "PRY", ".py"), SI("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SJ("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SK("COLOMBIA", "The Republic of Colombia", "CO", "COL", ".co"), SL("BOLIVIA", "The Plurinational State of Bolivia", "BO", "BOL", ".bo"), SM("SURINAME", "The Republic of Suriname", "SR", "SUR", ".sr"), SN("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SO("FRENCH GUIANA", "Guyane", "GF", "GUF", ".gf"), SP("PERU", "The Republic of Perú", "PE", "PER", ".pe"), SS("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SU("URUGUAY", "The Oriental Republic of Uruguay", "UY", "URY", ".uy"), SV("VENEZUELA", "The Bolivarian Republic of Venezuela", "VE", "VEN", ".ve"), SW("BRAZIL", "The Federative Republic of Brazil", "BR", "BRA", ".br"), SY("GUYANA", "The Co-operative Republic of Guyana", "GY", "GUY", ".gy"), TA("ANTIGUA AND BARBUDA", "Antigua and Barbuda", "AG", "ATG", ".ag"), TB("BARBADOS", "Barbados", "BB", "BRB", ".bb"), TD("DOMINICA", "The Commonwealth of Dominica", "DM", "DMA", ".dm"), TF("TRINIDAD AND TOBAGO", "The Republic of Trinidad and Tobago", "TT", "TTO", ".tt"), TG("GRENADA", "Grenada", "GD", "GRD", ".gd"), TI("VIRGIN ISLANDS (U.S.", "#N/A", "#N/A", "#N/A", "#N/A"), TJ("PUERTO RICO", "The Commonwealth of Puerto Rico", "PR", "PRI", ".pr"), TK("SAINT KITTS AND NEVI", "#N/A", "#N/A", "#N/A", "#N/A"), TL("SAINT LUCIA", "Saint Lucia", "LC", "LCA", ".lc"), TN("NETHERLANDS ANTILLES", "#N/A", "AN", "#N/A", "#N/A"), TQ("PUERTO RICO", "The Commonwealth of Puerto Rico", "PR", "PRI", ".pr"), TT("TRINIDAD AND TOBAGO", "The Republic of Trinidad and Tobago", "TT", "TTO", ".tt"), TU("VIRGIN ISLANDS (BRIT", "#N/A", "#N/A", "#N/A", "#N/A"), TV("SAINT VINCENT AND TH", "#N/A", "#N/A", "#N/A", "#N/A"), TX("BERMUDA", "Bermuda", "BM", "BMU", ".bm"), UA("KAZAKHSTAN", "The Republic of Kazakhstan", "KZ", "KAZ", ".kz"), UB("AZERBAIJAN", "The Republic of Azerbaijan", "AZ", "AZE", ".az"), UC("KYRGYZSTAN", "The Kyrgyz Republic", "KG", "KGZ", ".kg"), UD("ARMENIA", "The Republic of Armenia", "AM", "ARM", ".am"), UE("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UG("GEORGIA", "Georgia", "GE", "GEO", ".ge"), UH("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UI("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UK("UKRAINE", "Ukraine", "UA", "UKR", ".ua"), UL("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UM("BELARUS", "The Republic of Belarus", "BY", "BLR", ".by"), UN("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UO("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UR("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), US("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UT("TURKMENISTAN", "Turkmenistan", "TM", "TKM", ".tm"), UU("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), UW("RUSSIAN FEDERATION", "The Russian Federation", "RU", "RUS", ".ru"), VA("INDIA", "The Republic of India", "IN", "IND", ".in"), VC("SRI LANKA", "The Democratic Socialist Republic of Sri Lanka", "LK", "LKA", ".lk"), VD("CAMBODIA", "The Kingdom of Cambodia", "KH", "KHM", ".kh"), VE("INDIA", "The Republic of India", "IN", "IND", ".in"), VG("BANGLADESH", "The People's Republic of Bangladesh", "BD", "BGD", ".bd"), VH("HONG KONG", "The Hong Kong Special Administrative Region of China[10]", "HK", "HKG", ".hk"), VI("INDIA", "The Republic of India", "IN", "IND", ".in"), VL("LAO PEOPLE'S DEMOCRA", "#N/A", "#N/A", "#N/A", "#N/A"), VM("MACAU", "The Macao Special Administrative Region of China[11]", "MO", "MAC", ".mo"), VN("NEPAL", "The Federal Democratic Republic of Nepal", "NP", "NPL", ".np"), VO("INDIA", "The Republic of India", "IN", "IND", ".in"), VQ("BHUTAN", "The Kingdom of Bhutan", "BT", "BTN", ".bt"), VR("MALDIVES", "The Republic of Maldives", "MV", "MDV", ".mv"), VT("THAILAND", "The Kingdom of Thailand", "TH", "THA", ".th"), VV("VIETNAM", "The Socialist Republic of Viet Nam", "VN", "VNM", ".vn"), VY("MYANMAR", "The Republic of the Union of Myanmar", "MM", "MMR", ".mm"), WA("INDONESIA", "The Republic of Indonesia", "ID", "IDN", ".id"), WB("MALAYSIA", "Malaysia", "MY", "MYS", ".my"), WI("INDONESIA", "The Republic of Indonesia", "ID", "IDN", ".id"), WM("MALAYSIA", "Malaysia", "MY", "MYS", ".my"), WP("EAST TIMOR", "#N/A", "TP", "#N/A", "#N/A"), WS("SINGAPORE", "The Republic of Singapore", "SG", "SGP", ".sg"), YA("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YB("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YC("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YD("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YE("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YF("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YG("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YH("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YI("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YJ("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YK("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YL("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YM("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YN("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YO("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YP("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YQ("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YR("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YS("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YT("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), YW("AUSTRALIA", "The Commonwealth of Australia", "AU", "AUS", ".au"), ZB("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZG("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZH("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZJ("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZK("KOREA DEMOCRATIC PEO", "#N/A", "KP", "#N/A", "#N/A"), ZL("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZM("MONGOLIA", "Mongolia", "MN", "MNG", ".mn"), ZP("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZS("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZU("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZW("CHINA", "The People's Republic of China", "CN", "CHN", ".cn"), ZY("CHINA", "The People's Republic of China", "CN", "CHN", ".cn");

        private String simpleCountryName;

        private String officialStateName;
        private String alpha2;
        private String alpha3;
        private String tld;


        IcaoCode(String simpleCountryName, String officialStateName, String alpha2, String alpha3, String tld) {
            this.simpleCountryName = simpleCountryName;
            this.officialStateName = officialStateName;
            this.alpha2 = alpha2;
            this.alpha3 = alpha3;
            this.tld = tld;
        }

        public String getSimpleCountryName() {
            return simpleCountryName;
        }

        public String getOfficialStateName() {
            return officialStateName;
        }

        public String getAlpha2() {
            return alpha2;
        }

        public String getAlpha3() {
            return alpha3;
        }

        public String getTld() {
            return tld;
        }
    }
    public enum Area {
        EEU, EUR, MES, PAC, SPA, CAN, USA, LAM, SAM, AFR;
    }

//testing
    public static void main(String[] args) {
        Country c1 = new Country(IcaoCode.VQ);
        System.out.println(c1.toString());
    }
}
