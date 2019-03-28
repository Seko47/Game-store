package com.example.projekt.config;

import com.example.projekt.models.*;
import com.example.projekt.repositories.*;
import com.example.projekt.services.StoreService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer
{
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameCategoryRepository gameCategoryRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StatusRepository statusRepository;

    @Bean
    InitializingBean init ()
    {
        return () -> {
            if ( this.roleRepository.findAll ()
                    .isEmpty () )
            {
                Role roleUser = this.roleRepository.save ( new Role ( Role.Types.ROLE_USER ) );
                Role roleEmployee = this.roleRepository.save ( new Role ( Role.Types.ROLE_EMPLOYEE ) );
                Role roleAdmin = this.roleRepository.save ( new Role ( Role.Types.ROLE_ADMIN ) );

                User admin = new User ( "admin", true );
                admin.setRoles ( new HashSet<> ( Arrays.asList ( roleAdmin, roleEmployee, roleUser ) ) );
                admin.setPassword ( this.passwordEncoder.encode ( "admin" ) );
                admin.setPasswordConfirm ( admin.getPassword () );
                admin.setCity ( "Warszawa" );
                admin.setPostCode ( "00-001" );
                admin.setStreet ( "Piękna" );
                admin.setHouseNumber ( "5" );
                admin.setEmail ( "test46114@gmail.com" );
                admin.setName ( "Admin" );
                admin.setSurname ( "Admin" );
                admin.setPhoneNumber ( "500100100" );
                admin.setCreditCardNumber ( "5572622554583573" );

                User employee = new User ( "employee", true );
                employee.setRoles ( new HashSet<> ( Arrays.asList ( roleEmployee, roleUser ) ) );
                employee.setPassword ( this.passwordEncoder.encode ( "employee" ) );
                employee.setPasswordConfirm ( employee.getPassword () );
                employee.setCity ( "Siedlce" );
                employee.setPostCode ( "08-100" );
                employee.setStreet ( "Normalna" );
                employee.setHouseNumber ( "10" );
                employee.setApartmentNumber ( "3" );
                employee.setEmail ( "employee@game_store.pl" );
                employee.setName ( "Employee" );
                employee.setSurname ( "Employee" );
                employee.setPhoneNumber ( "500100200" );
                employee.setCreditCardNumber ( "5574695661843138" );

                User user = new User ( "user", true );
                user.setRoles ( new HashSet<> ( Arrays.asList ( roleUser ) ) );
                user.setPassword ( this.passwordEncoder.encode ( "user" ) );
                user.setPasswordConfirm ( user.getPassword () );
                user.setCity ( "Warszawa" );
                user.setPostCode ( "00-002" );
                user.setStreet ( "Dziwna" );
                user.setHouseNumber ( "2" );
                user.setEmail ( "user@email.pl" );
                user.setName ( "User" );
                user.setSurname ( "User" );
                user.setPhoneNumber ( "505050505" );
                user.setCreditCardNumber ( "5387206745577036" );

                this.userRepository.save ( admin );
                this.userRepository.save ( employee );
                this.userRepository.save ( user );
            }

            if ( this.gameCategoryRepository.findAll ()
                    .isEmpty () )
            {
                GameCategory action = this.gameCategoryRepository.save ( new GameCategory ( 1, "akcji" ) );
                GameCategory arcade = this.gameCategoryRepository.save ( new GameCategory ( 2, "zręcznościowe" ) );
                GameCategory combat = this.gameCategoryRepository.save ( new GameCategory ( 3, "bijatyki" ) );
                GameCategory rpg = this.gameCategoryRepository.save ( new GameCategory ( 4, "RPG" ) );
                GameCategory strategy = this.gameCategoryRepository.save ( new GameCategory ( 5, "strategiczne" ) );
                GameCategory adventure = this.gameCategoryRepository.save ( new GameCategory ( 6, "przygodowe" ) );
                GameCategory sports = this.gameCategoryRepository.save ( new GameCategory ( 7, "sportowe" ) );
                GameCategory racing = this.gameCategoryRepository.save ( new GameCategory ( 8, "wyścigi" ) );
                GameCategory simulations = this.gameCategoryRepository.save ( new GameCategory ( 9, "symulacje" ) );
                GameCategory logic = this.gameCategoryRepository.save ( new GameCategory ( 10, "logiczne" ) );
                GameCategory social = this.gameCategoryRepository.save ( new GameCategory ( 11, "towarzyskie" ) );
                GameCategory mmo = this.gameCategoryRepository.save ( new GameCategory ( 12, "MMO" ) );
                GameCategory tpp = this.gameCategoryRepository.save ( new GameCategory ( 13, "TPP" ) );
                GameCategory sandbox = this.gameCategoryRepository.save ( new GameCategory ( 14, "sandbox" ) );
                GameCategory slasher = this.gameCategoryRepository.save ( new GameCategory ( 15, "slasher" ) );
                GameCategory historical = this.gameCategoryRepository.save ( new GameCategory ( 16, "historyczne" ) );
                GameCategory fantasy = this.gameCategoryRepository.save ( new GameCategory ( 17, "fantasy" ) );
                GameCategory economical = this.gameCategoryRepository.save ( new GameCategory ( 18, "ekonomiczne" ) );
                GameCategory car = this.gameCategoryRepository.save ( new GameCategory ( 19, "samochodowe" ) );
                GameCategory trucks = this.gameCategoryRepository.save ( new GameCategory ( 20, "ciężarówki" ) );
                GameCategory fpp = this.gameCategoryRepository.save ( new GameCategory ( 21, "FPP" ) );
                GameCategory horror = this.gameCategoryRepository.save ( new GameCategory ( 22, "horror" ) );
                GameCategory survival = this.gameCategoryRepository.save ( new GameCategory ( 23, "przetrwanie" ) );

                Game game1 = this.gameRepository.save ( new Game ( 1L,
                        "Assassin's Creed Odyssey", "Dziesiąta główna część bestsellerowej serii sandboksowych gier akcji, tym razem wykonująca gwałtowny zwrot w stronę gatunku RPG.",
                        "Assassin's Creed Odyssey to dziesiąta główna odsłona bestsellerowego cyklu sandboksów zapoczątkowanego w 2007 roku. W przeciwieństwie do poprzednich części serii, które były grami akcji, omawiana produkcja wykonała silny zwrot w stronę gatunku RPG, upodabniając się do gry Wiedźmin 3: Dziki Gon. Przemierzając antyczną Grecję, gracz m.in. podejmuje wybory moralne w misjach, odgrywa postać w interaktywnych dialogach i awansuje na kolejne poziomy doświadczenia. Za powstanie tytułu odpowiada znajdujące się w Quebec wewnętrzne studio firmy Ubisoft, które korzystało z pomocy wielu innych oddziałów tego francuskiego wydawcy.",
                        new BigDecimal ( 249.90 ),
                        "https://www.gry-online.pl/galeria/gry13/4619187.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/4115375.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/4113484.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/4111375.jpg",
                        "Ubisoft",
                        "Ubisoft",
                        new Date ( 118, 10, 5 ),
                        Arrays.asList ( rpg, tpp, sandbox, historical, action, slasher ) ) );

                Game game2 = this.gameRepository.save ( new Game ( 2L,
                        "Wiedźmin 3: Dziki Gon", "Gra action RPG, stanowiąca trzecią część przygód Geralta z Rivii. Podobnie jak we wcześniejszych odsłonach cyklu, Wiedźmin 3: Dziki Gon bazuje na motywach twórczości literackiej Andrzeja Sapkowskiego, jednak nie jest bezpośrednią adaptacją żadnej z jego książek.",
                        "Wiedźmin 3: Dziki Gon (The Witcher 3: Wild Hunt) na komputery osobiste to trzecia odsłona popularnej serii gier RPG akcji opartej na prozie Andrzeja Sapkowskiego. Tytuł wyprodukowało studio CD Projekt RED, czyli zespół odpowiedzialny również za dwie poprzednie części – Wiedźmina z 2007 roku i Wiedźmina 2: Zabójcy Królów z 2011 roku. Produkcja w wersji na PC-ty nie odbiega zawartością od konsolowych wydań, ale użytkownicy „blaszaków” otrzymali kilka niedostępnych na pozostałych platformach sprzętowych elementów. Przede wszystkim mogą oni cieszyć się najwyższą możliwą jakością oprawy graficznej oraz animację na poziomie 60 klatek na sekundę. Oczywiście, by korzystać z tych wszystkich dobrodziejstw, potrzebny jest odpowiednio mocny komputer. Drugim elementem, którego nie znajdziemy na konsolach są modyfikacje. Studio CD Projekt RED udostępniło w sierpniu 2015 roku zainteresowanym osobom oficjalne narzędzia moderskie, umożliwiające m.in. dowolne zmienianie obiektów, statystyk i nie tylko. Zaowocowało to m.in. tymi modami.",
                        new BigDecimal ( 139.90 ),
                        "https://cdn.gracza.pl/galeria/gry13/grupy/13689.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/521451313.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/521450392.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/521449519.jpg",
                        "CDP",
                        "CD Projekt RED",
                        new Date ( 115, 4, 19 ),
                        Arrays.asList ( action, rpg, tpp, fantasy, sandbox ) ) );

                Game game3 = this.gameRepository.save ( new Game ( 3L,
                        "Euro Truck Simulator 2",
                        "Sequel cieszącego się dużą popularnością symulatora jazdy ciężarówkami. Podobnie jak pierwowzór z 2008 roku, grę przygotowało czeskie studio SCS Software. Euro Truck Simulator 2 pozwala wcielić się w rolę kierowcy ciężarówki i zarazem właściciela firmy transportowej.",
                        "Euro Truck Simulator 2 to druga odsłona symulatora od SCS Software, w którym zasiadamy za sterami potężnych samochodów ciężarowych. Tytuł wprowadza szereg usprawnień w stosunku do poprzedniczki, które miały za zadanie urealnić rozgrywkę. Dla fanów tego typu pozycji cenną informacją powinien być fakt, że za grę odpowiadają między innymi ludzie pracujący wcześniej nad Hard Truck 18 Wheels of Steel – inną produkcją o tej tematyce.\n" +
                                "\n" +
                                "Autorzy projektując swoje dzieło skupili się przede wszystkim na dostosowaniu go do gustu europejskich odbiorców. Dzięki temu po raz drugi zwiedzamy tysiące kilometrów dróg ciągnących się przez Stary Kontynent, trafiając do wielu znanych miast. Rozgrywka tradycyjnie polega na dowiezieniu wyznaczonego ładunku we wskazane miejsce, robiąc to najlepiej w jak najkrótszym czasie, popisując się po drodze sztuką umiejętnego prowadzenia ciężarówki.\n" +
                                "\n" +
                                "Twórcy zadbali o licencje znanych marek, dzięki czemu pokierujemy pojazdami takich producentów jak Mercedes, Scania i wielu innych. Poszczególne ciężarówki przypominają zatem swoje rzeczywiste odpowiedniki. Bez problemu możemy także modyfikować ich wygląd. Pojawiła się bowiem możliwość aplikowania nowych halogenów i innych dodatków, a także zmiany malowania.\n" +
                                "\n" +
                                "Deweloper postawił na symulacyjne podejście do tematu, co daje się odczuć choćby za sprawą modelu jazdy. Dla miłośników tematu największą gratką pozostaje sterowanie przy użyciu widoku z kabiny, kiedy to można się najbardziej wczuć w swoją rolę. Twórcom udało się także przystosować sterowanie ciężarówką do układu przycisków na padzie, dzięki czemu gra staje się przystępna również dla graczy korzystających z tego typu kontrolerów.\n" +
                                "\n" +
                                "Istotnych zmian doczekała się oprawa graficzna działająca na zupełnie nowym silniku. Uwagę zwraca przede wszystkim dużo większa mapa, na której znajduje się więcej miast do zwiedzania. Autorzy postarali się o odwzorowanie najważniejszych elementów poszczególnych metropolii, dzięki czemu pewne miejsca możemy rozpoznać na pierwszy rzut oka. Poprawiona została także warstwa techniczna – ulepszono cieniowanie i dodano efekty HDR, których zabrakło w poprzedniej części. Całość została zoptymalizowana w taki sposób, aby gra działała także na nieco słabszych komputerach, choć wiąże się to z obniżeniem detali graficznych.",
                        new BigDecimal ( 69.90 ),
                        "https://www.gry-online.pl/galeria/gry13/1285715234.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/1286870140.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/1286869671.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/1286869250.jpg",
                        "Rondomedia",
                        "SCS Software",
                        new Date ( 112, 10, 19 ),
                        Arrays.asList ( simulations, economical, car, trucks ) ) );

                Game game4 = this.gameRepository.save ( new Game ( 4L,
                        "The Elder Scrolls V: Skyrim",
                        "Zrealizowana z dużym rozmachem gra action-RPG, będąca piątą częścią popularnego cyklu The Elder Scrolls. Produkcja studia Bethesda Softworks pozwala graczom ponownie przenieść się do fantastycznego świata Nirn.",
                        "The Elder Scrolls V: Skyrim to kolejna część serii cRPG autorstwa zespołu Bethesda Softworks. Ponownie odwiedzamy w niej kontynent Tamriel, a fabuła tym razem obraca się wokół powrotu do tej krainy pradawnej rasy smoków.\n" +
                                "\n" +
                                "Akcja toczy się 200 lat po wydarzeniach opowiedzianych w grze Oblivion. Gracze wcielają się w jednego z ostatnich bohaterów parających się profesją dovahkiina, czyli łowcy smoków. Do niedawna wydawało się, że potężne monstra są już tylko wspomnieniem, ale pewnego dnia pokryte łuskami bestie znów zaczynają pojawiać się na niebie, a przepowiednia zwiastuje nadejście Alduina, boga zniszczenia, który przyjmuje postać smoka. Walcząc z tymi groźnymi potworami przejmujemy ich dusze oraz zdolności, czyli tak zwane Krzyki. Za ich pomocą możemy spowolnić czas, odepchnąć przeciwników niewidzialną energią, a nawet wezwać na pomoc sprzymierzonego smoka. Wiele tego typu umiejętności otrzymujemy po odnalezieniu pradawnych słów, ukrytych w nordyckich podziemiach.\n" +
                                "\n" +
                                "Zabawę zaczynamy od stworzenia naszego wirtualnego alter-ego. Do wyboru jest dziesięć różnorodnych ras, brak natomiast podziału na klasy. Wraz z postępami w zabawie nasza postać staje się coraz potężniejsza. Po przejściu na kolejny poziom można samodzielnie zwiększyć jedną z trzech podstawowych cech: magię, wytrzymałość i zdrowie. Autorzy gry zupełnie przebudowali system umiejętności – zostały one podzielone na czytelne grupy, np. bronie jednoręczne, czy dwuręczne. Każda z takich kategorii zawiera konkretne zdolności i ataki. Zmiany zaszły także w systemie magii – zaklęcia ze szkoły Mistycyzmu zostały przeniesione do innych grup.\n" +
                                "\n" +
                                "W Skyrim pojawia się nowy system walki, który jest bardziej dynamiczny i jednocześnie wymaga myślenia taktycznego. Każdej z dłoni bohatera można teraz przypisać broń, tarczę lub zaklęcie. W ten sposób tworzymy wiele kombinacji, łącząc np. czar ofensywny w jednej ręce z mieczem w drugiej. Bezpośrednie starcia polegają już nie tylko na bezmyślnym wciskaniu klawiszy, ale odpowiednim manewrowaniu bohaterem, unikaniu lub parowaniu ciosów i powalaniu przeciwników. Herosi mogą z łatwością stracić równowagę i wystawić się na niebezpieczne uderzenia wrogów, którzy teraz są znacznie bardziej żywotni i groźni. Postać gracza potrafi też wyprowadzać efektowne ciosy wykańczające.\n" +
                                "\n" +
                                "Kolejna odsłona The Elder Scrolls jeszcze raz próbuje stworzyć wrażenie, że przemierzamy prawdziwy świat, zaludniony przez żywych mieszkańców. Decyzje gracza mają teraz poważniejsze konsekwencje, związane z ciekawszym zachowaniem innych postaci. Kiedy przykładowo wyrzucimy sztylet na środku miasta, to być może ktoś nam go odniesie lub wręcz przeciwnie - ukradnie. Zmianom uległy także dialogi, które stały się znacznie bardziej naturalne. Gdy znudzi nam się ratowanie świata możemy odpocząć przy bardziej przyziemnych czynnościach. The Elder Scrolls V: Skyrim pozwala zbierać plony z ziemi, wykuwać bronie w kuźni, gotować, wydobywać surowce w kopalniach, a nawet produkować pancerze.\n" +
                                "\n" +
                                "The Elder Scrolls V: Skyrim oferuje możliwość zwiedzania olbrzymiego wirtualnego świata, w którym znajdziemy m.in. pięć dużych miast i setki podziemi. W celu ułatwienia poruszania się po tak dużym terenie autorzy wprowadzili opcję szybkiej podróży pomiędzy lokacjami. Ponownie zastosowano system automatycznego dostosowania siły przeciwników do umiejętności naszego śmiałka, choć jednocześnie upewniono się, że nie będzie on tak inwazyjny jak w Oblivionie. Usprawniony silnik gry dobiera zadania pod kątem naszych dotychczasowych dokonań i siły postaci. Przykładowo, gdy kobieta prosi nas o odnalezienie porwanej córki system sprawdza, które podziemia już odwiedziliśmy, a następnie umieszcza dziewczynkę tam, gdzie dotąd nie zawitaliśmy, jednocześnie dodając takich przeciwników, by stanowili oni wyzwanie dla naszego bohatera.\n" +
                                "\n" +
                                "Gra korzysta z udoskonalonego silnika graficznego znanego z The Elder Scrolls IV: Oblivion. Znacznie poprawiono wygląd postaci oraz ich mimikę i gestykulację, dodano dynamiczne efekty pogodowe (m.in. śnieg) i symulację wiatru, który teraz realistycznie porusza gałęziami drzew. Gra oferuje opcję wyłączenia wszystkich elementów interfejsu z ekranu, pozwalając tym samym na głębsze zanurzenie się w wirtualny świat.",
                        new BigDecimal ( 159.99 ),
                        "https://www.gry-online.pl/galeria/gry13/1119867468.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/248707578.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/248706062.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/248702343.jpg",
                        "Bethesda Softworks",
                        "Bethesda Softworks",
                        new Date ( 111, 11, 11 ),
                        Arrays.asList ( rpg, fpp, tpp, fantasy, sandbox, action ) ) );

                Game game5 = this.gameRepository.save ( new Game ( 5L,
                        "Grand Theft Auto: San Andreas",
                        "Kontynuacja kontrowersyjnego cyklu Grand Theft Auto – jednej z najpopularniejszych serii wszechczasów, która pozwoliła graczom wcielić się w rolę drobnego rzezimieszka i rozsławiła w świecie gier firmę Rockstar Games.",
                        "Kolejna odsłona jednej z najpopularniejszych i najbardziej kontrowersyjnych gier wszech czasów, w której gracz ma okazję wcielić się w drobnego rzezimieszka, a następnie tworząc własny, kryminalny życiorys samemu stanąć na czele mafii. Tym razem, przychodzi nam wcielić się w niejakiego Carla Johnsona, który przed kilku laty uciekł z rodzinnego miasta w poszukiwaniu lepszego jutra. Po pięciu latach Carl wraca do Los Santos, w którym zastaje gangsterski świat morderstw, narkotyków i korupcji. Jednak nieciekawy krajobraz miasta to nie koniec złych wiadomości; dzielnica w której się wychował wygląda gorzej, niż pamiętał ją sprzed pięciu lat, a skłócona rodzina opłakuje zamordowaną matkę Carla. Na koniec złego, skorumpowani policjanci wrabiają naszego bohatera w morderstwo. Witaj w kalifornijskich latach 90-tych.\n" +
                                "\n" +
                                "Świat gry tworzy stan San Andreas, wraz z trzema miastami: Fierro (San Francisco), Las Venturra (Las Vegas) i Los Santos (Los Angeles), pomiędzy którymi gracz może się przemieszczać. Oczywiście miasta San Andreas nie są jedynie zlepkiem slumsów i prócz brudnych dzielnic metropolie posiadają także bogate rejony, w których mieszkają gwiazdy filmowe, politycy i milionerzy. Okradać jest więc kogo, Carl o tym wie, a na dodatek nie musi działać w pojedynkę, gdyż może rekrutować ludzi do swojego gangu. Możliwość wspólnej jazdy samochodem i piesze wycieczki naszej bandy, umożliwiają prowadzenia zaciekłych walk z konkurencyjnymi szajkami (np. gracz prowadzi auto, a w tym czasie pozostali pasażerowie prowadzą ostrzał konkurencji z broni maszynowej). Sam nastrój gry utrzymany jest w klimatach lat 90-tych, zaś fenomenalna grafika (realistyczna mgła oraz odbicia światła na karoserii wozów i realistyczne cieniowanie) i muzyka, okazują się niczym, wobec nowości jakie zostały wprowadzone do GTA: San Andreas w stosunku do poprzedniej części serii. Największą z nich, jest zapewne wprowadzenie do programu współczynnika głodu dla naszego bohatera. Na zapewnieniu sobie pożywiania jednak się nie kończy, prowadzona przez nas postać, np. poprzez nadmierne obżeranie się, może przybrać na wadze. To jednak nie koniec atrakcji, zbędne kilogramy można bowiem zrzucić, poprzez np. regularne wizyty w siłowni i zwiększoną dawkę sportu.\n" +
                                "\n" +
                                "Kolejnym urealnieniem świata gry jest większa interakcja ze znajdującymi się w miastach budynkami. Bardzo ważnym elementem rozgrywki okazują się włamania i kradzieże do mieszkań, które sami musimy zaplanować i skrzętnie przeprowadzić. Nie brakuje także możliwości zakupu przeróżnych lokali, które mogą dla nas zarabiać. Wśród nich znajduje się kasyno, w którym sami możemy przepuścić trochę gotówki. Każde miasto ma rozmiar porównywalny do całego Vice City z poprzedniej części GTA, nie brakuje więc różnorodnych środków transportu niezbędnych do przemierzania setek wirtualnych kilometrów. Do typowych pojazdów takich jak auta, motory, motorówki, helikoptery i samoloty, dołączył poczciwy i bardzo przydatny w świecie gry rower. Sam zaś Carl Johnson prócz biegania i prowadzenia auta, potrafi strzelać z dwóch pistoletów jednocześnie, a także, co jest jedną z największych innowacji wobec przedniej części – pływać.\n" +
                                "\n" +
                                "Pozytywne przeżycia związane ze zwiedzaniem miast San Andreas, związane są (nie licząc fenomenalnej grafiki) z inteligencją ich mieszkańców, którzy reagują np. na nasze nieetyczne zachowanie na ulicy, czy wyzywają nas od grubasów, gdy przedobrzymy z odżywianiem. Twórcy programu, jak sami zapewniają, aby wczuć się w klimat każdego z występujących w GTA: San Andreas miast, osobiście je zwiedzili i przenieśli do gry zaobserwowane tam, specyficzne atmosfery mentalne. Mimo gangsterskiej tematyki, GTA: San Andreas wprowadza do fabuły, także elementy humorystyczne, co tworzy z niej obowiązkową pozycję zarówno dla fanów serii, jak i nie przekonanych jeszcze do świata GTA graczy.",
                        new BigDecimal ( 259.90 ),
                        "https://www.gry-online.pl/galeria/gry13/75840406d.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/448022671.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/448022140.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/448021625.jpg",
                        "Rockstar Games",
                        "Rockstar Games",
                        new Date ( 105, 6, 7 ),
                        Arrays.asList ( action, tpp, car, sandbox ) ) );

                Game game6 = this.gameRepository.save ( new Game ( 6L,
                        "Resident Evil 2",
                        "Pełnoprawny remake gry Resident Evil 2, która pojawiła się w 1998 roku na PlayStation. Resident Evil 2 Remake opracowany został przez Capcom z myślą o nowoczesnych platformach sprzętowych, bazując na oryginalnej fabule oraz nieco zmodyfikowanej mechanice pierwowzoru.",
                        "Resident Evil 2 Remake to pełnoprawny remake drugiej części kultowej serii survival horrorów firmy Capcom, która ukazała się pierwotnie w 1998 roku na konsoli PlayStation, ale doczekała się także konwersji na komputery PC oraz konsole Dreamcast, Nintendo 64 i GameCube. Projektem odświeżenia legendarnej produkcji zajęło się główne studio deweloperskie Capcomu R&D Division 1, pod wodzą producenta Yoshiaki Hirabayashiego, który wcześniej pracował między innymi nad wydanym na początku 2015 roku Resident Evil HD.",
                        new BigDecimal ( 249.00 ),
                        "https://www.gry-online.pl/galeria/gry13/534672750.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/446356000.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/446351765.jpg",
                        "https://images9.gry-online.pl/galeria/galeria_duze3/446347328.jpg",
                        "Capcom",
                        "Capcom",
                        new Date ( 119, 0, 25 ),
                        Arrays.asList ( action, tpp, horror, survival, adventure ) ) );

                this.storeService.save ( new Store ( 1L, game1, 100 ) );
                this.storeService.save ( new Store ( 2L, game2, 100 ) );
                this.storeService.save ( new Store ( 3L, game3, 100 ) );
                this.storeService.save ( new Store ( 4L, game4, 100 ) );
                this.storeService.save ( new Store ( 5L, game5, 100 ) );
                this.storeService.save ( new Store ( 6L, game6, 100 ) );
            }

            if ( this.statusRepository.findAll ()
                    .isEmpty () )
            {
                this.statusRepository.save ( new Status ( 1, "oczekuje na realizację" ) );
                this.statusRepository.save ( new Status ( 2, "zrealizowane" ) );
                this.statusRepository.save ( new Status ( 3, "anulowane" ) );
            }

        };
    }
}