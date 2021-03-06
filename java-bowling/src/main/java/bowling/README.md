## 볼링 점수판 요구사항

### 기능 요구사항
* 1명 이상의 사용자가 사용할 수 있는 볼링게임 점수판을 구현한다.
* 각 프레임이 스트라이크이면 "X", 스페어이면 "9 | /", 미스이면 "8 | 1", 과 같이 출력하도록 구현한다.
    * 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    * 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    * 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.
* 스트라이크는 다음 2번의 투구까지 점수를 합산해야 한다. 스페어는 다음 1번의 투구까지 점수를 합산해야 한다.

### 구현 시작 방법
* 볼링 게임의 점수 계산 방식 아는 사람은 바로 구현을 시작한다.
* 점수 계산 방식을 모르는 사람은 구글에서 "볼링 점수 계산법"과 같은 키워드로 검색해 볼링 게임의 점수 계산 방식을 학습한 후 구현을 시작한다.

### 프로그램 실행 결과

```
How many people? 2
플레이어 1의 이름은?(3 english letters): PJS
플레이어 2의 이름은?(3 english letters): KYJ
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |      |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |
|  KYJ |      |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

PJS's turn : 10
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |
|  KYJ |      |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

KYJ's turn : 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |
|  KYJ |  8   |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

KYJ's turn : 2
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |
|  KYJ |  8|/ |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

PJS's turn : 8
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8   |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |
|  KYJ |  8|/ |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

PJS's turn : 2
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |
|      |  20  |      |      |      |      |      |      |      |      |      |
|  KYJ |  8|/ |      |      |      |      |      |      |      |      |      |
|      |      |      |      |      |      |      |      |      |      |      |

KYJ's turn : 10
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  PJS |  X   |  8|/ |      |      |      |      |      |      |      |      |
|      |  20  |      |      |      |      |      |      |      |      |      |
|  KYJ |  8|/ |  X   |      |      |      |      |      |      |      |      |
|      |  20  |      |      |      |      |      |      |      |      |      |

PJS's turn : 

...
```

### 프로그래밍 요구사항
* 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
* 객체지향 생활 체조 원칙
* 규칙 1: 한 메서드에 오직 한 단계의 들여쓰기만 한다.
* 규칙 2: else 예약어를 쓰지 않는다.
* 규칙 3: 모든 원시값과 문자열을 포장한다.
* 규칙 4: 한 줄에 점을 하나만 찍는다.
* 규칙 5: 줄여쓰지 않는다(축약 금지).
* 규칙 6: 모든 엔티티를 작게 유지한다.
* 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
* 규칙 8: 일급 콜렉션을 쓴다.
* 규칙 9: 게터/세터/프로퍼티를 쓰지 않는다.

## 기능 구현 목록

### Domain

#### Game 패키지
* SingleBowlingGame
    * 1명의 플레이어로 구성된 BowlingGame 객체이다.
    * 인스턴스 변수로 Frames와 Player를 가진다.
    * 현재 플레이중인 프레임의 투구가 끝났는지 반환한다.
    * 플레이어와 프레임의 점수를 포괄하는 Dto 객체를 생성한다.

* MultiBowlingGame
    * List<SingleBowlingGame> 의 일급 컬렉션이다.
    * 현재 플레이중인 게임을 확인하여 볼링을 진행하고, 현재 프레임이 종료되면 다음 프레임으로 이동한다.
    * List<Dto>를 제공한다.

#### Player 패키지
* Player
    * 게임 참가자 객체이다.
    * 이름이 3글자가 아닌 경우 예외를 발생시킨다.

#### Score 패키지
* FrameScore
    * 1개 프레임의 점수를 지닌 객체이다.
    * 점수(frameScore)와 보너스 투구회수(leftPitchCounts)가 정의되어있다.
    * 현재 프레임의 점수 결과(Spare Miss 등)에 따라서 점수 계산에 관여하는 추가 투구 회수를 다르다.
    * 추가 투구를 합산하여 점수가 합산되는 Frame의 경우, 기존 객체를 통해 새로운 객체의 점수를 누계하여 생성한다.
    * 점수 계산에 관여하는 투구 회수(leftPitchCounts)가 0일때 계산이 가능하다.

* FrameScores
    * List<FrameScore>의 일급 컬렉션이다.
    * 각 프레임은 오름차순으로 점수가 누계되어 표기되기 때문에, 누계한 결과를 리턴한다.
    
* PitchScore
    * 사용자가 매 프레임의 투구마다 입력하는 넘어뜨린 볼링핀의 개수를 정의한 객체이다.
    * 어플리케이션 상에서 허용되는 범위의 Score는 0부터 10이므로 캐싱한다.
    * 0 ~ 10 외의 점수로 Score 객체를 가져오려는 경우 예외를 발생시킨다.
    * 10점인지 0점인지를 구분한다.

* PitchScoreType
    * 1번의 투구를 통해 얻은 Score가 가질 수 있는 ScoreType(스트라이크, 스페어 등)을 정의한 enum이다.
    * 각 ScoreType별로 고유한 Signature 문자열을 가진다.
    * ScoreType은 투구(Pitch)의 순서, 즉 이전 투구의 결과에 영향을 받는다.
    * Score를 매개변수로 받아 적합한 ScoreType을 제시해준다.
        * 볼링핀 10개가 새로 주어졌을 때 첫 번째 투구인 경우(마지막 프레임 포함) : initiate
        * 두 번째 투구인 경우 : next

#### Pitch 패키지
* Pitch
    * 매 프레임에서 수행되는 1번의 투구를 정의한 객체이다.
    * 인스턴스 변수로 PitchScore와 그에 해당하는 PitchScoreType을 가진다.
    * Pitch 객체는 이전 Pitch의 결과에 따라 ScoreType이 달라지기 때문에, ScoreType과 마찬가지로 생성자를 경우의 수로 나눈다.
        * 볼링핀 10개가 새로 주어졌을 때 첫 번째 투구인 경우(마지막 프레임 포함) : initiate
        * 두 번째 투구인 경우 : next
    * 현재 보유하고 있는 Score 및 ScoreType을 토대로 상태를 판단하고 ScoreType의 시그니처를 반환한다.

* Pitches
    * 각 프레임들이 가질 List<Pitch>의 일급 컬렉션의 인터페이스.

* NormalPitches
    * 1 ~ 9번 프레임이 가지는 List<Pitch> 일급 컬렉션이다.
    * 투구를 할 때 유효성 검사를 한다.
    * 첫 번째 투구인지, 두 번째 투구인지 판단하여 적합한 Pitch 객체를 추가한다.
    * Pitch 객체들이 가지고 있는 Score Signature들의 List를 반환한다.

* FinalPitches
    * 10번 프레임이 가지는 List<Pitch> 일급 컬렉션이다.
    * 이전 투구가 스페어거나 스트라이크일 때 Pitch 객체를 initiate하여 이전 투구에 영향을 받지 않도록 한다.
    * 2번 투구를 마쳤을 때 스페어나 스트라이크가 없으면 더이상 진행할 수 없다.
    * 3번 투구를 마친 경우 더이상 진행할 수 없다.

#### Frame 패키지
* Frame
    * 각 프레임에 대한 인터페이스.
    
* NormalFrame
    * 1~9번 프레임.
    * 인스턴스 변수로 다음 프레임의 주소를 가진다.
    * 2번 투구를 하거나 스트라이크인 경우, 다음 프레임으로 이동할 수 있음을 알린다.
    * 현재 객체를 통해 다음 객체를 생성하며, 기존 객체의 인덱스를 이용한다.
        * 9번 객체가 다음 프레임을 생성하는 경우 FinalFrame을 리턴한다.
    * 현재 상태에서 다음 프레임으로 넘어갈 수 있는지 조건을 확인한다.
    * 현재 프레임의 최종 점수가 계산이 가능하면 점수를 반환한다.
    * 만약 스페어나 스트라이크와 같이 다음 프레임의 투구 결과가 점수 합산에 영향을 끼친다면, 다음 프레임에게 계산 작업을 위임한다.
        * 프레임이 가지고 있는 투구 회수와 위임받은 점수 객체의 계산 가능 여부를 종합 고려하여 점수를 계산한다.
    * 아직 계산 결과가 도출되지 못하는 상황이면 Optional.empty()를 반환한다.
    
* FinalFrame
    * 10번 프레임.
    * 2번 투구를 했을 때 스페어나 스트라이크가 없으면 경기 종료.
    * 3번 투구를 마무리한 경우 경기 종료.
    * 마지막 프레임에서의 스페어나 스트라이크는 별도의 보너스 점수 없이 단순 합만을 제시한다.
    * 그 외의 로직은 NormalFrame과 동일하다.

* Frames
    * List<Frame> 일급 컬렉션이다.
    * 현재 Play중인 Frame에게 bowl을 요청한다.
    * 다음 프레임으로 넘어갈 수 있는지를 확인한다.
    * 다음 프레임으로 넘어간다.
    * 현재 확인 가능한 프레임의 숫자 점수 리스트(FramesScores)와, 점수 시그니쳐 리스트(List<String>)을 반환한다.
    
#### Exception 패키지
* 커스텀 예외 패키지.
    
#### DTO 패키지
* ScoreSignaturesDto
    * 1개 프레임의 볼링 시그니쳐 (-, /, X 등)List<String> 을 가진 Dto 객체이다.

* BowlingGameDto
    * 현재 플레이어의 이름과, 현재 보유하고 있는 프레임들의 시그니쳐 리스트(List<ScoreSignaturesDto>) 및 
    프레임의 계산 가능한 점수 리스트(List<Integer>)를 가진 Dto 객체이다.
    
### View
* InputView
    * 참가자의 수를 입력받는다.
    * 참가자의 수만큼 플레이어의 이름을 입력받는다.
    * 선수별 프레임의 투구를 입력받는다.
    
* OutputView
    * 볼링 점수판을 그린다.
    
* ViewMessages
    * View에 필요한 상수를 정의해놓은 클래스이다.

### Controller
* Application
