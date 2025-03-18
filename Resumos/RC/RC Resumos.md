# #1 Introdução
### 1.1 O que é a internet? 
É uma rede de redes, um conjunto de WAN’s (Wide Area Network) ao qual estão
ligadas muitas redes locais, as quais estão ligadas a hosts ou end systems que se
conectam através de ISP’s (Internet Service Provider). A Internet permite aplicações
distribuídas correndo nos end systems a trocar dados com outras aplicações.

Os **protocolos** controlam o envio e a recepção de mensagens (e.g., TCP, IP, HTTP, Skype, Ethernet)

**Normas da Internet**:  - RFC: Request for Comments 
                  - IETF: Internet Engineering Task Force

A infraestrutura de comunicação possibilita aplicações distribuídas:  Web, VoIP, email, jogos, e-comércio, partilha de ficheiros

Serviços de comunicação fornecidos às aplicações: - entrega de dados fiável da origem ao destino                                                                                   - entrega de dados nãofiável (best effort)

Protocolos: - Humanos
		  - De rede (máquinas em vez de pessoas)

### 1.2 Extremidade da rede (edge)
Extremidade da rede: terminais e aplicações
Redes de acesso, meio físico: ligações cabladas e sem fios (wireless)
Núcleo da rede: -routers interligados 
             -rede de redes
Terminais (hosts): correm aplicações (programas) (e.g. Web, email)
Modelo cliente/servidor: terminal cliente faz pedidos e recebe serviços de um servidor sempre ligado (e.g. browser/servidor web; cliente/servidor de email)
Modelo peer-to-peer (p2p): uso mínimo ou ausência total de servidores dedicados (e.g. Skype; BitTorrent, …)

Connection oriented service (TCP - Transmission Control Protocol):
Efectua o handshaking: Estabelecimento a priori da conexão. Assim sendo, um programa
cliente e um programa servidor enviam control packets um ao outro de forma a preparar
ambos os programas para um fluxo de mensagens contendo os dados a enviar.
Este serviço tem:
- Controlo de fluxo (Flow Control): que garante que nenhum dos lados recebe uma
enchente de pacote, ou seja, emissor não envia mais do que o receptor não pode
processar.
- Controlo de congestionamento (Congestion Control): Emissores reduzem a taxa de
envio quando a rede está congestionada.

Connectionless service (UDP – User Datagram Protocol): Ao contrário do serviço
anterior este não estabelece o handshaking, ele simplesmente envia os pacotes, não
havendo também respostas. Em termos de tempo pode ser mais rápido. O transporte
dos dados é não-fiável, sem controlo de fluxo, nem controlo de congestionamento.

Aplicações TCP: HTTP, FTP, Telnet, SMTP.
Aplicações UDP: teleconferência, streamming, DNS, telefone via Internet.

Acesso residencial:
DSL: linha física dedicada até à central telefónica
Multiplexagem nas frequências: canais diferentes transmitidos em bandas de frequência diferentes
Cabo: normalmente fornecido por empresas de TV por cabo
Fibra Óptica (FTTH): Ligações ópticas da central até a casa

Acesso empresarial:
Redes locais (LANs): liga os terminais ao router
Redes de acesso sem fios (wireless): rede de acesso wireless partilhada liga os terminais ao router
Redes domésticas


### 1.3 Núcleo da rede (core)
Comutação de circuitos (Circuit switching):
Ligação de rede por fios sempre que dois hosts querem fazer uma ligação a rede,
reserva uma conexão end-to-end dedicada. Os circuitos podem ser implementados por
FDM (Frequency Division Multiplexing) ou TDM (Time Division Multiplexing).
- FDM: a frequência. É dividida através de conexão estabelecida pelo link
(capacidade nas ligações e nos comutadores – Bandwidth).
- TDM: o tempo é dividido em frames por uma duração fixa e cada frame é dividido
num numero fixo de slots.

Comutação de pacotes (Packet switching):
Durante a comunicação entre o emissor e o destino, os pacotes atravessam links de
comunicação e routers (packet switches). A maioria dos routers usa store-and-forward
transmission, ou seja, recebe o pacote todo antes de começar a transmitir. Para cada
link, o router tem um output buffer (ou output queue) alem disso, os pacotes sofrem de
output buffer queuing delays. Estes delays são variáveis e dependem do
congestionamento da Internet.
A sequencia de pacotes não tem um padrão fixo, a capacidade é distribuída consoante a
necessidade no momento - Multiplexagem estatística.

Packet switching vs Circuit switching:
CS é adequado para vídeo-conferência e serviços com tempo real, porém o PS oferece
melhor partilha de bandwidth e é mais simples, mais eficientes e com menor custo de
implementação.
A comutação de pacotes permite ter mais utilizadores na rede.

Message segmenting: acontece quando o emissor segmenta longas mensagens de
aplicação e divide-as em pacotes menores. Isto é um tipo de packet-switching. Uma das
maiores vantagens do packet-switching com segmented messages é que o delay é
muito menor em comparação ao message-switching devido a este ultimo enviar os
pacotes um a um, e o primeiro manda paralelamente.

Velocidade de transmissão (capacidade): numero de bits por segundo que o circuito é
capaz de transmitir (bits/segundo).
Taxa de erros: quociente do numero de bits transmitido com erro sobre o numero total
de bits transmitido.
Dimensão máxima/mínima de um pacote: maior/menor pacote que o circuito admite
que seja transmitido (MTU: Maximum Transfer Unit).

### 1.4 Atrasos, perdas e throughput em redes de comutação de pacotes
Como ocorrem atrasos e perdas?
Os pacotes são colocados em filas de espera nos routers. Taxa de chegada de pacotes
ao link excede a sua capacidade. Os pacotes ficam na fila, à espera da sua vez. Se, não
existir espaço livre quando chega um pacote, é eliminado.
Tipos de atrasos (delays):
- Processamento nos nós (Processing delay): tempo necessário para analisar o
header do pacote e determinar para onde dirigir o pacote. Pode também incluir o
tempo para verificar os erros aos níveis dos bits.
- Filas de espera (Queuing delay): depende do numero de pacotes que chegaram
primeiro e estão a espera de transmissão no link.
- Atraso de transmissão (Transmission delay): assumindo que os pacotes são
transmitidos em FIFO, o pacote só será transmitido após os outros terem sido
todos transmitidos, sendo conseguido o tempo através de l(dimensão do pacote)
/ r(taxa de transmissão do link).
- Atraso de propagação (Propagation delay): o tempo que o bit demora entre
routers.
O tempo total de transmissão de um nó é a soma de todos estes tempos acima.

Perda de pacotes:
Um pacote pode chegar e encontrar a fila de espera do router preenchida, nesse caso o
router deixará cair o pacote e este será perdido.

### 1.5 Camadas protocolares, modelos de serviço
Pilha protocolar da Internet:
- Aplicação: aplicações em rede (FTP, SMTP, HTTP).
- Transporte: transferência de dados entre processos (TCP, UDP).
- Rede: encaminhamento dos datagramas entre a origem e o destino (IP,
protocolos de routing).
- Ligação lógica: transferência de dados entre elementos de rede adjacentes (PPP,
Ethernet, WIFI).
- Ligação física: sinais no meio representando bits

# #2 Camada de Aplicação
### 2.1 Princípios das aplicações em rede
Arquitectura cliente-servidor:
- Servidor:
o Sempre ligado.
o Endereço IP fixo.
o replicação de servidores para escalabilidade (custo)
- Cliente:
o Comunica com o servidor.
o Não precisa de estar sempre ligado.
o Pode ter endereço de IP dinâmico.
o Não comunica directamente com outros clientes.

Arquitectura P2P pura:
- Ausência de servidores: os end systems comunicam directamente entre si.
- Os peers: podem não estar sempre ligados e mudar de endereço de IP.
- Os peers: funcionam simultaneamente como cliente e servidor web transitórios.
- Exemplo: Gnutella.

Arquitectura híbrida:
- Skype:
o Aplicação P2P.
o Servidor central permite encontrar o endereço da outra extremidade da
comunicação.
o Fluxos de voz directos entre pares de clientes (não atravessam o servidor.
- Instant messaging:
o As mensagens de texto são enviadas directamente (P2P).
o Serviço central de detecção de presença e localização.
 O utilizador regista o seu IP no servidor central quando se liga.
 O utilizador contacta o servidor central para descobrir os IP’s dos
amigos.

Comunicação entre processos:
Um processo é um programa a correr numa máquina. Dentro da mesma máquina, dois
processos comunicam usando os métodos de IPC definidos pelo SO. Processos em
máquinas diferentes comunicam através da troca de mensagens.
- Cliente: processo que inicia activamente a comunicação.
- Servidor: processo que espera passivamente ser contactado.
Nota: as aplicações P2P funcionam simultaneamente como cliente e servidor.

Endereçamento de processos:
O endereço de IP do terminal onde corre o processo não é suficiente para identificar o
processo. Assim, o identificador do processo inclui tanto o endereço IP como o numero
da porta associado ao processo no terminal.

Sockets (API – Application programmers interface):
- Interface entre uma camada de aplicação e uma camada de transporte.
- Um socket permite a comunicação de, e entre processos.
- Os dados enviados para um socket podem ser lidos na outra extremidade.
- UDP – Datagram Sockets, TCP – Stream Sockets.
- Cada extremidade de um socket é identificada por um IP e uma porta.

Protocolo de aplicação define:
- Tipos de mensagens trocadas.
- Sintaxe das mensagens.
- Semântica das mensagens.
- Regras de como e quando devem responder os processos às mensagens.
- Protocolos de domínio publico: definidos em RFC’s possibilitam interoperação
entre sistemas, e.g HTTP, SMTP.
- Protocolos proprietários: só tem acesso a eles quem o detentor dos direitos
decidir, e.g., Skype.

Serviço dos protocolos de transporte na Internet
Serviço TCP:
- Orientado as conexões (Connection Oriented): exige o estabelecimento prévio de
conexão entre emissor e receptor.
- Transporte fiável.
- Controlo de fluxo: o emissor não envia mais rápido que o receptor consegue
processar.
- Controlo de congestionamento: redução de débito quando a rede está
sobrecarregada.
- Não tem: garantias de atraso máximo nem de débito mínimo.
Serviço UDP:
- Transporte não fiável (best effort).
- Delineação de mensagens: um datagrama UDP corresponde a uma mensagem.
- Não tem: estabelecimento prévio de conexões, fiabilidade, controlo de fluxo,
controlo de congestionamento, garantias de atraso máximo ou de débito mínimo.

### 2.2 Web e HTTP
HTTP (Hypertext transfer protocol):
Protocolo de nível de aplicação que usa o transporte TCP. O cliente abre uma conexão
TCP para a porta 80 do servidor. É stateless, o servidor não mantém nenhuma
informação sobre as conexões dos clientes.

Persistência das conexões HTTP:
Existem dois tipos de conexão que podem ser utilizadas no HTTP, sendo que o
HTTP/1.0 usa somente conexão persistente.
- Conexão persistente: Existem duas versões.
o Sem PIPELINING: onde o cliente envia um novo pedido somente quando a
resposta anterior foi recebida, sendo que demora 1 RTT (Round Trip Time).
Uma das desvantagens é, após enviar um pedido a rede fica bloqueada
enquanto espera pela resposta.
o Com PIPELINING: o cliente envia um request no momento que achar uma
referencia, e pode fazer vários requests enquanto não receber os
anteriores (back-to-back). O servidor ao responder também envia a
resposta em (back-to-back).

- Conexão não-persistente: Aqui o que acontece é que o cliente inicia uma conexão
TCP com o servidor, de seguida envia um HTTP request através do socket
associado à conexão TCP. O servidor recebe o request, vai buscar o objecto
requerido, encapsula-o num HTTP response e envia para o cliente, ordenando
depois que a conexão termine, porém, a conexão só termina quando se
assegurar que o ficheiro chegou em condições. Após a recepção a conexão
termina. Tempo total = 2RTT + tempo de transmissão do ficheiro.

Métodos HTTP
HTTP/1.0:
- GET: Pede um recurso estático ao servidor.
- POST: Envia ao servidor dados para processar.
- HEAD: Pede ao servidor apenas os cabeçalhos.
HTTP/1.1:
- GET,POST,HEAD
- PUT: upload de um ficheiro para o URL especificado.
- DELETE: apagar o ficheiro especificado.

Web caches (proxy server)
Objectivo: satisfazer pedidos dos clientes sem recorrer ao servidor original.
- User configura o browser para usar proxy.
- O browser envia todos os pedidos HTTP ao proxy
o Objecto está na cache: proxy devolve-o directamente.
o Caso contrario, pede objecto ao servidor original, devolve-o ao browser e
guarda-o em cache.

- Proxy actua como cliente e como servidor.
- Normalmente é instalado pelo ISP.
- Um proxy pode também registar ou filtrar pedidos dos utilizadores.
- O próprio browser também faz caching
o Acessos repetidos a mesma pagina.
Porquê fazer caching?
- Reduz o tempo de resposta aos pedidos.
- Reduz tráfego no acesso à Internet.
- Abundância de caches permite mesmo aos servidores de baixa capacidade
funcionar aceitavelmente.

### 2.3 FTP
FTP: File Transfer Protocol
- Transferir ficheiros de/para um servidor remoto.
- Modelo cliente/servidor.
o Cliente: lado que inicia a transferência (em qualquer das direções).
o Servidor: máquina remota.
- Norma do ftp: RFC 959.
- Porta do servidor ftp: 21.

FTP: conexões de controlo e de dados
- O cliente liga-se ao servidor FTP na porta 21 (usando TCP como protocolo de
transporte).
- Autenticação do utilizador na conexão de controlo.
- O cliente navega no sistema de ficheiros remoto enviando comandos na conexão
de controlo.
- Quando o servidor recebe um comando de transferência de ficheiro abre outra
conexão TCP para transferir o ficheiro.
- Após a transferência, a segunda conexão é terminada
- Para transferir um novo ficheiro, o servidor abre outra conexão TCP de dados.
- Controlo “out of band” (numa conexão separada).
- O servidor FTP mantém informação de estado (directório corrente, autenticação).

### 2.4 Correio electrónico
Correio electrónico
Um serviço de correio electrónico é composto por três grandes componentes: USER
AGENTS, MAIL SERVERS e pelo protocolo SMTP (Simple Mail Transfer Protocol). No
envio de uma mensagem os user agents: dão permissão ao utilizador para ler,
responder, reencaminhar, gravar e compor mensagens. Podem também ser chamados
de mail readers. Ao acabar de compor a mensagem, o user agent envia a mensagem
para o mail reader, onde vai ser colocada na sua fila de espera de saída de mensagens.
Quando o receptor quiser ler a mensagem, o seu user agent vai buscar a mensagem à
mailbox do seu mail server.
Os mail servers formam basicamente o núcleo duma infra-estrutura de emails. Cada
utilizador tem uma caixa de correio localizada num mail server, sendo ela que mantém e
gere as mensagens que lhe foram enviadas. Se o mail server do emissor não conseguir
enviar a mensagem para o mail server do receptor, deixa a mensagem numa fila de
espera e tenta envia-la mais tarde . Se passadas muitas tentativas a mensagem ainda
não tiver sido enviada, o mail server remove a mensagem e manda um mail ao emissor
a notificar que não foi entregue.

SMTP
- Usa conexões persistentes (TCP).
- Exige que a mensagem (cabeçalho e corpo) estejam em formato ASCII de 7 bits.
o O que requer que as mensagens binárias multimédia sejam codificadas em
ASCII antes de serem enviadas com o SMTP e descodificadas novamente
em binário depois do envio. Sendo que o protocolo de HTTP não necessita
que a data multimédia seja codificada em ASCII.
- No SMTP a conexão é directa. No seu transporte o SMTP faz:
1. O cliente SMTP faz uma conexão por TCP à porta 25 do servidor SMTP.
2. O servidor e o cliente estabelecem um handshaking.
3. Durante o handshaking o cliente indica o endereço de email do emissor e
do receptor.
4. Terminado o handshaking, o cliente envia a mensagem por TCP.
5. Se houver mais mensagens, o cliente envia-as pela mesma conexão TCP.
Se não, dá a instrução para fechar a conexão.

Comandos SMTP
- HELO message - o cliente identifica-se.
- MAIL from: address - endereço de origem.
- RCPT to: address - endereço de destino.
- DATA...... - mensagem.
- QUIT - fecha a conexão.
- CRLF.CRLF - indica o fim da mensagem.
- EXPN address - expande o endereço local.
- VRFY address - verifica se existe localmente.

Protocolos de acesso ao email: POP3
O POP3 é um protocolo do SMTP. Este começa quando o user agent do cliente abre
uma conexão TCP ao mail server do servidor. Após a conexão estar estabelecida
existem três fases:
1. Autorização: o user agent envia um username e uma password para autenticar o
utilizador de fazer download do email.
2. Transacção: o user agent busca a mensagem, alem disso ele pode marcar as
mensagens para apagar, remover essas mesmas marcas, e obter estatísticas.
Nesta fase o user agent envia comandos, e o servidor responde com dois tipos de
resposta, +OK (sendo seguido às vezes dos dados do servidor do cliente) e -
ERR (usado pelo servidor que indica que houve um erro com o comando
anterior).
3. Update: ocorre depois do cliente ter enviado um comando quit, terminando a
sessão POP3.

### 2.5 DNS
DNS: Domain Name System
É uma base de dados implementada numa hierarquia de servidores de nome e protocolo
de camada de aplicação que permite aos hosts e nomes de servidor comunicarem de
modo a fornecerem um serviço de tradução. Este serviço foi criado devido ao crescente
no de hosts.

DNS: caches e actualização de registos
- Quando um servidor aprende a resolução de um nome, guarda essa resolução
em cache.
o Entradas na cache são removidas após um certo tempo (timeout).
o Os servidores TLD estão quase sempre em cache nos servidores locais.
 Os servidores de raiz raramente são contactados.
- As actualizações aos registos são normalmente feitas pelo administrador da rede.

DNS records (registos)
Os name servers que implementam as bases de dados distribuídas DNS guardam
Resource Records. O formato dum Resource Record é, (name, value, type TTL). TTL
significa Time-To-Live. O significado para o nome e o valor dependem do tipo:
- Tipo A: o nome é um host name, o valor é um endereço IP do host.
- Tipo NS: o nome é um domínio, o valor é um host name de um servidor do
domínio.
- Tipo CNAME: o nome é um alias para o nome real (nome canónico), o valor é o
nome canónico.
- Tipo MX: o valor é o nome de um mail server do domínio e a respectiva
prioridade.

### 2.6 Aplicações P2P
sem servidores dedicados 
 terminais comunicam directamente entre si 
 os peers podem não estar sempre ligados e mudar de endereço IP 
 muito escalável ❖ aumento simultâneo na procura e na oferta 
 difícil de gerir ❖ nós ligados intermitentemente ❖ endereços mudam

### 2.7 Programação com sockets — TCP
Programação com sockets
Socket: uma “porta” entre o processo (aplicação) e um protocolo de transporte end-to-
end (TCP ou UDP).
Alguma terminologia:
- Uma stream é uma sequência de caracteres que entra ou sai de um processo.
- Uma stream de entrada esta associada a uma fonte de entrada para o processo
(teclado ou socket).
- Uma stream de saída está associada a uma fonte de saída (monitor ou socket).

Programação com sockets: TCP
Exemplo de aplicação cliente-servidor:
• Cliente lê pedido da entrada-padrão (stream inFromUser) e envia-o ao servidor
através da socket (stream outToServer)
• servidor lê uma linha da socket
• servidor converte a linha para maiúsculas e devolve o resultado ao cliente
• cliente lê da socket (stream inFromServer) a linha modificada e imprime-a.

### 2.8 Programação com sockets — UDP
Programação com sockets: UDP
Não há estabelecimento de conexão entre cliente e servidor.
- Não há handshaking.
- O emissor especifica o endereço IP e a porta de destino de cada pacote enviado.
- O servidor extrai o endereço IP e a porta de origem do pacote com o pedido para
saber para onde deve enviar a resposta.
UDP: a informação transmitida pode perder-se, ser recebida for a de ordem ou mesmo
ser duplicada.
Para a aplicação: o UDP fornece um serviço de transferência não-fiável de blocos de
informação (“datagramas”) entre o cliente e o servidor.



# #3 Camada de Transporte
### 3.1 Serviços da camada de transporte
###### -Serviços e protocolos de transporte
Fornecer um canal lógico de comunicação entre processos a correr em máquinas
diferentes.
Protocolo de transporte a correr nos terminais:
- Emissor: partir mensagens de aplicação em segmentos que passa à camada de
rede.
- Receptor: reconstruir mensagens a partir dos segmentos e passá-las à camada
de aplicação.
As aplicações dispõem de mais que um protocolo de transporte.
- Internet: TCP e UDP.

###### -Camada de transporte vs camada de rede
Camada de rede:
- Comunicação lógica entre máquinas.
Camada de transporte:
- Comunicação lógica entre processos. Usa o serviço da camada de rede e, sobre
ele implementa um serviço de valor acrescentado.

### 3.2 Multiplexagem e desmultiplexagem
###### -Desmultiplexagem
Envio dos segmentos de dados no respectivo socket de cada processo, o que implica
que cada socket seja único e que cada segmento tenho campos onde se indique o
respectivo socket.

###### -Desmultiplexagem sem conexões
- Sockets associadas a portas: DatagramSocket mySocket1 = new
DatagramSocket(12534).
- Socket UDP identificada pelo par: (end. IP de destino, porta de destino).
- Quando uma máquina recebe um segmento UDP:
o Verifica a porta de destino no cabeçalho do segmento.
o Passa o segmento UDP à socket associada a essa porta.
- Datagramas com diferentes endereços IP ou portas de origem são passados à
mesma socket.

###### -Multiplexagem Orientada a conexões
- Socket TCP identificada pela quadra ordenada:
o Endereço IP de origem
o Porta de origem
o Endereço IP de destino
o Porta de destino
- Máquina receptora usa todos estes quatro valores para selecionar a socket
apropriada.
- O servidor pode atender múltiplas conexões TCP em simultâneo:
o Cada socket é identificada por uma quadra ordenada própria
- E.g servidores Web usam sockets diferentes para cada cliente
o Conexões HTTP não persistentes usam uma nova socket por cada pedido.


### 3.3 Transporte sem conexões: UDP
###### -UDP: User Datagram Protocol
- Serviço “best-effort” que só acrescenta multiplexagem e desmultiplexagem ao
nível de rede. As mensagens UDP (datagramas UDP) podem ser perdidos ou
entregues fora de ordem.
- Ausência de conexão (“connectionless”): não é necessário nenhum
estabelecimento de conexão (“handshaking”) entre o emissor e o receptor e cada
datagrama é processado independentemente dos outros.

###### -Mais sobre o UDP
- Frequentemente usado em aplicações de streaming multimédia
o Tolerantes a perdas
o Sensíveis ao debito
- Outros usos para o UDP:
o DNS
o SNMP
- Transferência fiável sobre UDP
o A fiabilidade tem que ser implementada na camada de aplicação.
o Recuperação de erros especifica da aplicação.
- Erros em UDP
o Perda de pacotes
o Os frames com erros são geralmente recusados
o Os protocolos de transporte mascaram os erros.

![[Pasted image 20241119150449.png]]

###### -Controlo de erros no UDP: checksum
Objetivo: detectar “erros” (e.g., bits invertidos) no segmento transmitido.
Emissor:
- Trata o conteúdo do segmento como uma sequencia de inteiros de 16bits.
- Checksum: adição (em complemento para 1) do conteúdo do segmento
(colocando zeros no próprio campo checksum).
- Coloca o valor calculado no campo de checksum UDP.
Receptor:
- Calcula o checksum do segmento recebido.
- Compara o checksum calculado com o recebido:
o Diferentes – erro detectado.
o Iguais – não foram detectados erros: podem haver erros não detectados,
mas probabilidade é muito baixa.
![[Pasted image 20241119150724.png]]
![[Pasted image 20241119150826.png]]

### 3.4 Princípios da transferência de dados fiável
![[Pasted image 20241119150908.png]]
![[Pasted image 20241119150938.png]]
![[Pasted image 20241119150950.png]]
![[Pasted image 20241119151055.png]]
 ###### -RDT2.0: canal com erros de bit.
O canal pode inverter bits no pacote (Checksum permite detectar erros de bit).
Como recuperar erros?
- Confirmações (ACKs): o receptor indica explicitamente o emissor que o pacote foi
correctamente recebido.
- Confirmações negativas (NACKs): o receptor indica explicitamente ao emissor
que o pacote continha erros. O emissor retransmite o pacote quando recebe um
NACK.
Novidades no RDT2.0 (em relação ao 1.0):
- detecção de erros
- feedback do receptor: mensagens de controlo (ACK, NACK) enviadas do receptor
para o emissor.
![[Pasted image 20241119151145.png]]
![[Pasted image 20241119151204.png]]
![[Pasted image 20241119151220.png]]

###### -RDT2.0 tem um problema grave:
E se um ACK ou NACK se corromper?
- o emissor não sabe o que aconteceu no receptor.
- Não pode simplesmente retransmitir, pois isso podia dar origem a um duplicado.
Lidar com duplicados:
- o emissor retransmite o pacote corrente se o ACK ou NACK tiverem erros.
- Envia um número de sequência em cada pacote.
- O receptor ignora os duplicados (não os entrega à aplicação).
Stop and wait: O emissor envia um pacote e espera pela resposta do receptor.

![[Pasted image 20241119151310.png]]
![[Pasted image 20241119151404.png]]

###### -RDT2.1 – Discussão
Emissor:
- # de sequência adicionado ao pacote.
- Dois #s (0,1) são suficientes. Porquê?
- Tem que verificar se há erros no ACK/NACK recebido.
- Duas vezes mais estados
o O estado tem que se lembrar se o # de seq. do pacote actual é 0 ou 1.

Receptor:
- tem que verificar se o pacote recebido é um duplicado
o o estado indica o # de seq. esperado do próximo pacote.
- Nota: o receptor não tem maneira de saber se o seu ultimo ACK ou NACK foi
recebido correctamente no emissor.

###### -RDT2.2: um protocolo sem NACKs.
A mesma funcionalidade do RDT2.1, mas usando apenas ACKs.
Em vez do NACK, o receptor envia um ACK para o último pacote recebido sem erros.
- o receptor tem que incluir explicitamente o numero de sequencia do pacote
actual.
Um ACK duplicado no emissor tem o mesmo efeito dum NACK: retransmissão do pacote
actual.

![[Pasted image 20241119152330.png]]

###### -RDT3.0: canais com erros e perdas
Novo pressuposto: o canal pode também perder pacotes (dados ou ACKs).
- o checksum, o # de sequencia, ACKs e retransmissões ajudam mas não
chegam...
Aproximação: o emissor espera um período de tempo “razoável” pelo ACK.
- se não chegar nesse tempo, retransmite.
- Se o pacote (ou o ACK) apenas tiverem atrasado (e não perdido):
o A retransmissão é um duplicado, mas os #s de sequencia já tratam disso.
o O receptor tem que especificar no ACK o # de sequência do pacote
confirmado.
o É necessário um temporizador.

![[Pasted image 20241119152412.png]]

![[Pasted image 20241119152426.png]]
![[Pasted image 20241119152514.png]]
![[Pasted image 20241119152523.png]]
![[Pasted image 20241119152545.png]]
![[Pasted image 20241119152723.png]]
![[Pasted image 20241119152700.png]]
![[Pasted image 20241119153537.png]]

###### -Protocolos com pipelining
Pipelining: possibilidade de haver múltiplos pacotes em transito (sem receber o ACK dos
anteriores)
- É preciso aumentar a gama de números de sequência.
- Bufferização no emissor e/ou no receptor.
![[Pasted image 20241119153619.png]]

![[Pasted image 20241119153707.png]]
![[Pasted image 20241119153956.png]]
![[Pasted image 20241119154006.png]]

### 3.5 Transporte orientado a conexões: TCP
![[Pasted image 20241119161259.png]]
![[Pasted image 20241119161323.png]]
![[Pasted image 20241119162313.png]]

###### -TCP: transferência fiável de dados
- O TCP fornece um serviço de transporte fiável por cima do serviço não fiável do
IP.
- Com pipelining.
- As confirmações são comulativas.
- O TCP usa apenas um temporizador de retransmissão.
- Retransmissões despoletadas por:
o Timeouts.
o Confirmações duplicadas.
- Inicialmente consideremos um emissor TCP simplificado:
o Ignora ACKS duplicados.
o Sem controlo de fluxo nem de congestionamento.

![[Pasted image 20241119162552.png]]
![[Pasted image 20241119162612.png]]
![[Pasted image 20241119162629.png]]
![[Pasted image 20241119162654.png]]
![[Pasted image 20241119162830.png]]
![[Pasted image 20241119162840.png]]
