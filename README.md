- # ActiveMQ, Kafka ve RabbitMQ karşılaştırın. Örnek kodlar ile nasıl çalıştığını anlatın. (10 Puan)
   - `RabbitMQ:`
      - RabbitMQ Producer ve Consumer olmak üzere iki aktörü mevcuttur.
         - Producer: Gönderilen mesajın sahibidir.
         - Consumer: Mesajın alıcısıdır.
         - Queue: Mesajların rabbitMQ üzerinde depolandığı ve sıraya eklendiği kuyruktur.
         - Publisher mesajı publish ettikten sonra mesajı RabbiMQ’daki exchange karşılar.
           Exchange aldığı mesajı rabbitMQ içerisindeki ilgili route üzerinden kuyruğa yönlendirir.
           Mesajın exchange’ten kuyruğa nasıl gideceğinin bilgisi route üzerinde tanımlanır.
           Mesajlar queue’de sıralanır. Queue bilindiği gibi FIFO mantığına sahiptir. Kuyrultaki mesajlar sırasıyla
           consumer’a iletilir.
         - RabbitMq şu senaryolarda daha iyidir:
         - Farklı protokoller kullanılmak isteniyorsa, (AMQP, STOMP, MQTT, AMQP 1.0)
         - Yüksek performansa ihtiyaç varsa
         - Kolay bir entegrasyon isteniyorsa
         - Mesajların ulaşmasını garanti olarak isteniyorsa
         - Daha az sayıda mesajlaşma mevcutsa
      - RabbitMQ Dezavantajları
         - Yüksek hacimli bir mesajlaşma sistemi için uygun değildir.
         - Consumer’lar sürekli online durumda kabul edilir. Eğer mesaj iletilmezse mesajıbeklemede olarak işaretler.
         - Mesajlar persistent olarak saklanmak isteniyorsa RabbitMQ persistent modunda kullanılmalıdır. Bunun dışında
           RabbitMQ restart edildiğinde kuyruktaki tüm mesajlar kaybolacaktır.
   - `Kafka:`
      - Kafka şu senaryolarda daha iyidir:
         - RabbitMQ’da anlatılan konseptin benzerine sahiptir. Kafka da message broker yazılımıdır. Broker kendisinden
           bir
           mesaj gönderilmesi istendiğinde o mesajı gönderir ancak ulaşıp ulaşmadığını kontrol etmez. Queue
           içerisindeki takip cursor’unun nerede kaldığı belli değildir. Bunu bilmesi gereken consumer’dır. Kafka
           genellikle rabbitMQ’ya göre daha büyük ölçekli mesajlaşma uygulamalarında veya streaming
           uygulamalarında
           kullanılır. Streaming gibi servislerde tercih edilmesinin sebebi kuyruktaki mesajların kaybolmamasıdır ve
           persistent olarak saklanmasıdır. Örneğin client izlediği bir videoyu geri sardığında broker, eski
           mesajarı
           kolayca getirip consumer’a iletebilir.
         - Consumer’lar mesajı almak için anlık olarak broker’ı tetiklemek durumundaysa
         - Mesajlar kaybolmamalıysa (Persistency)
         - Kolay ölçeklenebilir bir yapı isteniyorsa
         - Aynı anda çok sayıda fazla mesajlaşma yapılıyorsa
         - Cursor’un queue’da nerede kaldığını consumer’ın kontol etmesi isteniyorsa

      - Kafka Dezavantajları
         - Native Kafka Protokol adında tek bir mesajlaşma protolü kullanır.
         - .NET için 3rd party bir sdk kullanılmalıdır. Desteği resmi olarak mevcut değildir.

   - `ActiveMQ:`
      - Active MQ, amacı uygulamalar arasında güvenli ve güvenilir bir şekilde veri alışverişini sağlamak olan
        geleneksel mesaj aracılarından biridir. Az miktarda veri ile ilgilenir ve bu nedenle iyi tanımlanmış mesaj
        formatları ve işlemsel mesajlaşma için uzmanlaşmıştır.
         - Aktif MQ/Artemis Kullanım Durumları
         - Günde yalnızca az sayıda iletiyi işleyin
         - Yüksek düzeyde güvenilirlik ve işlemsellik
         - Anında veri dönüşümleri
```
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-activemq</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
      </dependency>
      <dependency>
        <groupId>org.springframework.kafka</groupId>
        <artifactId>spring-kafka</artifactId>
      </dependency>
```
- # Microservis ve monolith mimariyi karşılaştırın.(10 Puan)
   - ## Monolitik Mimari Nedir?
      - Monolitik Mimari, geleneksel uygulama geliştirme yöntemi olarak kabul edilir. Monolitik mimaride bir uygulama
        tek bir paket olarak geliştirilmiştir. Normal bir uygulamanın geliştirilmesi, modüler katmanlı veya altıgen
        mimari ile başlar. Bu mimari, aşağıdaki gibi farklı katman türlerinden oluşur:
         - Sunum Katmanı: Grafik Kullanıcı Arayüzü katmanı, HTML veya XML/JSON kullanarak HTTP isteklerini işler.
         - İş Katmanı: Uygulamanın iş mantığı bu katmanda bulunur.
         - Veritabanı Erişim Katmanı: Uygulamaların tüm veritabanı erişimleri bu katmanda gerçekleşir.
         - Uygulama Entegrasyon Katmanı : Diğer sistemlerle yapılan tüm yazılım entegrasyonları bu katmanda gerçekleşir.
         - Monolitik Mimari mantıksal katmanlı bir mimariye sahip olsa da, nihai uygulamalar tek bir monolit halinde
           paketlenecek ve ardından dağıtılacaktır. Monolitik uygulamalar uygun modülerlikten yoksundur ve yalnızca tek
           bir kod tabanına sahiptir.
   - ## Mikroservis Mimarisi nedir?
      - Microservis mimarisi, çeşitli uygulamalar geliştirmek için modüler bir yaklaşım izler.
        Bir Mikro Hizmet Mimarisi, çeşitli hizmetleri gerçekleştiren bir dizi küçük, bağımsız ve özerk modülden oluşur.
        Her hizmet, ilgili iş birimlerini bağımsız olarak uygulama yeteneğine sahip olmalıdır. Monolitik mimari tek bir
        birimdir.
        Ancak Mikro hizmet mimarisi, toplu olarak tek bir uygulama olarak çalışan bir grup küçük bağımsız üniteye
        sahiptir.
        Bir uygulamanın tüm işlevleri, API'ler ile birbiriyle konuşan ayrı ve bağımsız konuşlandırılabilir modüllere
        bölünür.
        Bir Mikro Hizmetler mimarisindeki hizmetlerin her biri bağımsız olarak ölçeklenebilir, dağıtılabilir ve kolayca
        güncellenebilir.
      - Bunları kodlamak için birden fazla programlama dili kullanılabilir. Ayrıca, veri depolamak için farklı bir
        depolama türü kullanabilirler.

| Mikroservis                                                                                                            | Monolitik                                                                                                                |
|------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| Her hizmet, farklı programlama dilleri kullanılarak bağımsız olarak geliştirilebilir.                                  | Tamamen tek bir programlama dilinde geliştirilmiştir.                                                                    |
| Anlaşılabilirliği yüksektir ve bakımı çok kolaydır.                                                                    | Anlamak çok zor ve kafa karıştırıcı.                                                                                     |
| Uygulamanın tamamını ölçeklendirmeden her hizmet ayrı ayrı ölçeklenebildiğinden uygulama ölçeklendirmesi çok kolaydır. | Uygulamanın tamamının ölçeklendirilmesi gerektiğinden uygulama ölçeklendirmesi çok zordur.                               |
| Sürekli geliştirme ve dağıtım mümkündür.                                                                               | Sürekli geliştirme ve dağıtım çok karmaşıktır.                                                                           |
| Her hizmetin kendi veri modelini benimsemesine izin veren birleşik bir veri modeline sahiptir.		                       | Merkezi bir veri modeline sahiptir.                                                                                      |
| Son derece tutarlı ve hazır.	                                                                                          | Nispeten daha az tutarlı ve kullanılabilir, çünkü herhangi bir güncelleme, geliştirme sürecini sıfırdan gerektirecektir. |

# SOAP - RESTful karşılaştırın (10 Puan)

| SOAP                                                                                                                                                                                                                                                                                                            | RESTful                                                                                                                                                                                                                                                                                                                                                                                                                             |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| SOAP , REST'ten önce tasarlanmış ve devreye girmiş bir protokoldür.<br/> SOAP'ı tasarlamanın arkasındaki ana fikir, farklı platformlar ve programlama dilleri üzerine kurulmuş programların kolay bir şekilde veri alışverişi yapabilmesini sağlamaktı.<br/> SOAP, Basit Nesne Erişim Protokolü anlamına gelir. | REST , medya bileşenleri, dosyalar ve hatta belirli bir donanım aygıtındaki nesneler gibi bileşenlerle çalışmak için özel olarak tasarlanmıştır.<br/>REST ilkelerine göre tanımlanmış herhangi bir web servisi RestFul web servisi olarak adlandırılabilir.<br/> RestFul bir hizmet, gerekli bileşenlerle çalışmak için GET, POST, PUT ve DELETE'nin normal HTTP fiillerini kullanır. REST, Temsili Durum Transferi anlamına gelir. |
| SOAP bir protokoldür.                                                                                                                                                                                                                                                                                           | REST ise bir mimari modeldir.                                                                                                                                                                                                                                                                                                                                                                                                       |
| SOAP, kullanımı için daha fazla bant genişliğine ihtiyaç duyar.                                                                                                                                                                                                                                                 | REST'in fazla bant genişliğine ihtiyacı yoktur.                                                                                                                                                                                                                                                                                                                                                                                     |
| SOAP bir protokol ve REST bir mimari model olduğundan, SOAP REST'i kullanamaz                                                                                                                                                                                                                                   | REST, SOAP'ı web servisleri için temel protokol olarak kullanabilir, çünkü sonuçta sadece bir mimari modeldir.                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                                                                                                                      |
| SOAP sadece XML formatı ile çalışabilir. SOAP mesajlarından da görüldüğü gibi iletilen tüm veriler XML formatındadır.                                                                                                                                                                                           | REST, Text, HTML, XML, JSON vb. gibi farklı veri biçimlerine izin verir. Ancak veri aktarımı için en çok tercih edilen biçim JSON'dur.                                                                                                                                                                                                                                                                                              |
| SOAP, işlevselliğini istemci uygulamalarına göstermek için hizmet arabirimlerini kullanır. SOAP'ta WSDL dosyası, istemciye web hizmetinin hangi hizmetleri sunabileceğini anlamak için kullanılabilecek gerekli bilgileri sağlar.                                                                               | REST, donanım aygıtındaki bileşenlere erişmek için Tekdüzen Hizmet konumlandırıcılarını kullanır. Örneğin, bir URL'de barındırılan bir çalışanın verilerini http://isbasi.com olarak temsil eden bir nesne varsa, bunlara erişmek için mevcut olabilecek URI'lerden bazıları aşağıdadır. <br/>  - http://isbasi.com/personal <br/> -http://isbasi.com/personal/1                                                                    |
| SOAP, güvenlik sağlama konusunda daha iyidir.                                                                                                                                                                                                                                                                   | REST, SOAP gibi herhangi bir güvenlik dayatmaz. Bu nedenle REST, herkese açık URL'ler için çok uygundur, ancak istemci ve sunucu arasında gizli verilerin aktarılması söz konusu olduğunda, REST web hizmetleri için kullanılacak en kötü mekanizmadır                                                                                                                                                                              |
| Standardizasyon, güvenlik, genişletilebilirlik	                                                                                                                                                                                                                                                                 | Yüksek Performans, Ölçeklenebilirlik, Esneklik ve tarayıcı dostu                                                                                                                                                                                                                                                                                                                                                                    |
| Daha karmaşık, düşük performans, daha az esneklik	                                                                                                                                                                                                                                                              | Dağıtılmış ortamlar için uygun değil, daha az güvenlik                                                                                                                                                                                                                                                                                                                                                                              |
| Finansal hizmetler, kurumsal düzeyde uygulamalar, ödeme ağ geçitleri, yüksek güvenlikli uygulamalar, telekomünikasyon hizmetleri için daha uygun                                                                                                                                                                | Web hizmetleri, sosyal ağlar ve mobil hizmetler için genel API'ler için daha uygun.                                                                                                                                                                                                                                                                                                                                                 |
# isbasi.com üye olup sisteme 3 yeni model ekleyin ve mimariye uygun şekilde CRUD işlemleri yapan endpointleri yazın. (50 Puan)

- Stok hizmet sayfası altındaki Hizmet ve ürün-> Product olarak modellendi.
- Para sekmesi Kasa -> Till
- Para sekmesi Çek -> Cheque

# CustomerServisi için gerekli tüm endpoint’leri yazın. (10 Puan)

- `CommercialConroller` olarak düzenledi.

# Aktif ve pasif müşterileri listeleyen endpoint için gerekli kodu yazın. (10 Puan)

- `CommercialController aktif-pasif listeleme için getCommercialByIsActive hepsi için getAllCommercials`
