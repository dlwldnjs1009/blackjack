# blackjack

| 클래스 | 책임 |
| --- | --- |
| `Card` | 하나의 카드 정보를 표현 (무늬 + 숫자) |
| `CardNumber` | 카드 숫자 및 점수 계산 규칙 정의 (Ace 1/11 포함) |
| `Suit` | 카드 무늬 정보 표현
| `Deck` | 전체 카드 덱 관리 및 카드 제공 |
| `Hand` | 참가자가 가진 카드 목록 관리 및 점수 계산, 블랙잭/버스트 판단 |
| `Participant` | 공통 로직 추상화 (딜러/플레이어) |
| `Player` | 사용자 입력 기반으로 행동 결정 |
| `Dealer` | 16 이하일 때 무조건 카드 받는 로직 포함 |
| `BlackjackGame` | 게임의 전체 흐름 관리, 초기화/턴 진행/결과 출력 |

## 블랙잭 Ace 처리 규칙 (여러 Ace 포함)

1. 에이스는 11 또는 1로 계산할 수 있다.
2. 여러 개의 에이스가 있다면:
    - 모든 에이스를 **일단 1로 가정**하고 총합을 계산한다.
    - 총합이 11 이하이면 → **첫 번째 에이스만 11로 바꿔 총합 + 10** 한다.
    - 그렇지 않으면 모든 에이스는 1로 계산.

## 기능 목록

### 1. 게임 준비

사용자로부터 플레이어 이름 목록을 입력받는다. (쉼표 구분)

각 플레이어의 베팅 금액을 입력받는다.

딜러와 플레이어에게 카드 2장을 분배한다.

### 2. 카드 로직

카드(Card)의 숫자와 무늬(Suit)를 표현한다.

점수를 계산할 수 있는 카드 숫자(CardNumber)를 정의한다.

A는 1 또는 11로 계산된다.

J, Q, K는 10으로 계산된다.

### 3. 사용자 행위

플레이어는 추가 카드를 받을지 선택할 수 있다.

카드의 총합이 21을 초과하면 버스트(Bust) 처리된다.

블랙잭 여부(초기 두 장이 21점)를 판단한다.

### 4. 딜러 행위

딜러는 카드 합이 16 이하이면 반드시 한 장을 더 받는다.

카드 합이 17 이상이면 더 이상 받지 않는다.

21 초과 시 모든 남은 플레이어가 승리한다.

### 5. 승패 및 수익 계산

각 플레이어의 점수와 딜러 점수를 비교해 승패를 결정한다.

블랙잭일 경우 베팅 금액의 1.5배를 획득한다.

딜러와 동시에 블랙잭인 경우 베팅 금액을 돌려받는다.

버스트된 플레이어는 베팅 금액을 잃는다.

최종 수익 결과를 출력한다.

### 6. 도메인 설계

`Card` 클래스 구현

`Deck` 클래스에서 카드 뽑기 기능 구현

`Hand` 클래스에서 점수 계산 기능 구현

`Participant` (플레이어/딜러 공통) 추상화

`Player`와 `Dealer`는 Participant를 상속 구조로 구현

`BlackjackGame` 클래스에서 게임 흐름 제어
