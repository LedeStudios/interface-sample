# IsekaiItem 관리 API 사용 설명서

## 개요
'IsekaiItem 관리 API'는 Minecraft 서버에서 아이템을 생성하고 관리하며, 다양한 방식으로 아이템을 조작하는 기능을 제공한다.
이 API는 아이템의 ID, ItemStack, Material 등을 기반으로 아이템을 생성할 수 있으며, NBT 태그, 이름, 설명, 인챈트, 내구도, 플래그 등을
설정할 수 있다.

## 각 기능의 사용 방법 및 상세 설명   

### 아이템 생성
아이템을 생성하는 방법은 세 가지가 있는데, 상황에 따라 적절히 선택하도록 한다.

- 'ItemStack'을 사용하여 아이템 생성.
  ```java
  ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
  IsekaiItem item = IsekaiItem.of(diamondSword);
  ```
  - 이미 생성된 'ItemStack'이 있다면 이를 기반으로 'IsekaiItem'을 생성할 수있다.
  

- 'ItemsAdder'의 ID를 사용하여 아이템 생성.
  ```java
  IsekaiItem customItem = IsekaiItem.of("my_item_id");
  ```
  - 'ItemsAdder'에 등록된 ID를 사용하여 커스텀 아이템을 생성할 수 있다.
  

- 'Material'을 사용하여 아이템 생성.
  ```java
  IsekaiItem basicItem = IsekaiItem.of(Material.DIAMOND_SWORD);
  ```
  - 'Material'은 Minecraft에서 사용되는 기본적인 아이템 유형으로 바닐라 아이템을 생성할 때 유용하다.

### 아이템 등록 여부 확인
아이템에 'ItemsAdder' 시스템에 올바르게 등록되었는지 확인하거나, 등록된 아이템의 ID를 조회할 수 있다.

- 'ItemsAdder'에 아이템이 등록되어 있는지 확인.
  ```java
  boolean isRegistered = item.isRegisterd();
  ```
  - 커스텀 아이템이 'ItemsAdder' 시스템에 정상적으로 등록되었다면 'true', 그렇지 않다면 'false'를 반환한다.


- 아이템의 ID를 확인.
  ```java
  Optional<String> itemId = customItem.getId();
  itemId.ifPresent(id -> System.out.println("아이템 ID: " + id));
  ```
  - 아이템이 'ItemsAdder'에 등록된 경우, 해당 아이템의 ID를 조회할 수 있다. ID가 없다면 'Optional.empty()'가 반환된다.

### NBT 태그 관리
NBT 태그는 Minecraft 아이템에 커스텀 데이터를 저장하는 방법으로, 이 기능을 사용하여 아이템에 추가 정보를 저장하고 관리할 수 있다.

- NBT 태그 설정
  ```java
  item.setTag(new NamespacedKey("myplugin", "damage"), PersistentDataType.INTEGER, 50);
  ```
  - 특정 키와 값을 사용하여 NBT 태그를 설정할 수 있다. 이 태그는 아이템에 커스텀 정보를 추가하는 데 사용된다.
  

- NBT 태그 값 조회
  ```java
  Integer damageValue = item.getTag(new NamespacedKey("myplugin", "damage"), PersistentDataType.INTEGER);
  ```
  - 설정된 태그 값을 조회할 수 있다. 태그가 없다면 'null'이 반환된다.
  

- NBT 태그 존재 여부 확인
  ```java
  boolean hasDamageTag = item.hasTag(new NamespacedKey("myplugin", "damage"));
  ```
  - 아이템에 특정 태그가 존재하는지 확인한다. 태그가 있다면 'true', 없다면 'false'를 반환한다.
  
### 기본 아이템 설정 관리
아이템의 이름, 설명, 개수 등을 쉽게 설정할 수 있다.

- 아이템 이름 설정
  ```java
  item.setName(Component.text("예제용 검"));
  ```
  - 아이템의 이름을 설정한다. 이 이름은 인벤토리에서 아이템을 식별하는 데 사용되며, 유저가 확인할 수 있다.
  

- 아이템 설명 설정
  ```java
  item.setLore(List.of(Component.text("이런 식으로 설명을 추가할 수 있다."), Component.text("줄 바꿈도 이런 식으로.")));
  ```
  - 아이템의 설명을 설정한다. 여러 줄의 설명을 추가할 수 있으며, 이 또한 유저가 확인할 수 있다.


- 아이템 개수 설정
  ```java
  item.setAmount(10);
  ```
  - 아이템의 개수를 설정하는 기능이다.
  
### 인챈트 관리
아이템에 인챈트를 부여하거나 제거할 수 있다.

- 인챈트 부여
  ```java
  item.setEnchantment(new IsekaiEnchantmentImpl(Enchantment.DAMAGE_ALL, 5));
  ```
  - 아이템에 특정 인챈트를 레벨 값을 포함하여 부여한다.
  

- 인챈트 제거
  ```java
  item.removeEnchantment(new IsekaiEnchantmentImpl(Enchantment.DAMAGE_ALL, 5));
  ```
  - 아이템에서 특정 인챈트를 제거한다.
  

- 특정 인챈트 존재 여부 확인
  ```java
  boolean hasSharpness = item.hasEnchantment(new IsekaiEnchantmentImpl(Enchantment.DAMAGE_ALL, 5));
  ```
  - 아이템에 특정 인챈트가 존재하는지 확인한다. 존재하면 'true', 그렇지 않으면 'false'를 반환한다.


- 모든 인챈트 목록 조회
  ```java
  Set<IsekaiEnchantment> enchantments = item.getEnchantments();
  enchantments.forEach(enchant -> System.out.println("인챈트: " + enchant.enchantment().getKey() + " 레벨: " + enchant.level()));
  ```
  - 아이템에 부여된 모든 인챈트 목록을 조회한다. 각 인챈트의 종류와 레벨이 포함된다.


- 모든 인챈트 제거
  ```java
  item.clearEnchantments();
  ```
  - 아이템에 부여된 모든 인챈트를 한 번에 제거한다.

### 내구도 관리
내구도를 설정하거나 조회할 수 있다.

- 내구도 설정
  ```java
  item.setDurabillity(1500);
  ```
  - 아이템의 내구도를 설정한다.
  

- 내구도 조회
  ```java
  int durability = item.getDurability();
  if (durability != -1) {
    System.out.println("아이템의 내구도: " + durability);
  }
  ```
  - 아이템의 현재 내구도를 확인할 수 있다. 내구도가 설정되어 있지 않다면 '-1'이 반환된다.
  
### 플래그 관리
플래그는 아이템의 시각적 또는 기능적 속성을 제어하는 데 사용된다. 예를 들어, 인챈트를 숨기거나 특정 정보를 감출 수 있다.

- 플래그 설정
  ```java
  item.setFlags(ItemFlag.HIDE_ENCHANTS);
  ```
  - 아이템에 특정 플래그를 설정하여, 아이템의 속성 또는 정보를 감출 수 있다.


- 플래그 존재 여부 확인
  ```java
  boolean hasHideEnchants = item.hasFlag(ItemFlag.HIDE_ENCHANTS);
  ```
  - 아이템에 특정 플래그가 설정되어 있는지 확인한다. 설정되어 있으면 'true', 그렇지 않다면 'false'를 반환한다.


- 모든 플래그 제거
  ```java
  item.clearFlags();
  ```
  - 아이템에 설정된 모든 플래그를 제거한다.
  
### 아이템 스택 생성 및 복제
'ItemStack'은 인벤토리에 저장되는 아이템의 기본 단위로, 이 기능을 통해서 'IsekaiItem'을 'ItemStack'으로 생성하거나 복제할 수 있다.

- ItemStack으로 생성
  ```java
  ItemStack itemStack = item.toItemStack();
  ```
  - 현재 'IsekaiItem'을 'ItemStack' 객체로 생성하여, Minecraft의 기본 인벤토리 시스템과 호환되도록 한다.


- 아이템 복제
  ```java
  IsekaiItem clonedItem = item.copy();
  ```
  - 현재 'IsekaiItem'을 복제하여 동일한 속성을 가진 새로운 'IsekaiItem' 인스턴스를 생성한다.
  
## 사용 시 주의사항
- NBT 태그를 설정할 때, 데이터 타입과 실제 값을 일치시켜야 한다. 예를 들어, 'PersistentDataType.INTEGER'를 사용할 때는 실제 데이터도 정수여야 한다.
- 'ItemsAdder' 시스템을 사용하는 경우, 아이템을 생성하기 전에 ID가 시스템에 등록되어 있는지 확인해야 한다.

## 예제 코드

```java
// ItemsAdder의 ID를 사용하여 새로운 아이템 생성
IsekaiItem item = IsekaiItem.of("my_item_id");

// NBT 태그 설정: 커스텀 데미지 값 추가
item.setTag(new NamespacedKey("myplugin", "damage"), PersistentDataType.INTEGER, 50);

// 아이템 이름과 설명 설정
item.setName(Component.text("예제용 검"));
item.setLore(List.of(Component.text("이런 식으로 설명을 추가할 수 있다."), Component.text("줄 바꿈도 이런 식으로.")));
        
// 인챈트와 내구도 설정
item.setEnchantment(new IsekaiEnchantmentImpl(Enchantment.DAMAGE_ALL, 5));
item.setDurability(1500);

// 플래그 설정: 인챈트 숨기기
item.setFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

// 아이템이 ItemsAdder에 등록되었는지 확인
if (item.isRegistered()) {
    System.out.println("아이템이 등록되었습니다.");
}

// 아이템을 ItemStack으로 변환
ItemStack itemStack = item.toItemStack();

// 아이템 복제
IsekaiItem clonedItem = item.copy();
```
---
# IsekaiPlayer 관리 API 사용 설명서

## 개요
'IsekaiPlayer 관리 API'는 Minecraft 서버에서 플레이어의 역할, 상태, 인벤토리 등을 관리하는 다양한 기능을 제공한다.
플레이어의 타입을 판단하고, 닉네임 설정, 버프 효과 지급 등 다양한 작업을 수행할 수 있다.

## 각 기능의 사용 방법 및 상세 설명

### 플레이어 인스턴스 가져오기
플레이어의 'UUID'를 사용하여 'IsekaiPlayer' 인스턴스를 가져올 수 있다. 이 인스턴스는 플레이어의 모든 정보와 기능에 접근하는 데 사용된다.

- UUID를 사용하여 플레이어 인스턴스 가져오기
  ```java
  UUID playerUUID = UUID.fromString("player-uuid-string");
  IsekaiPlayer player = Isekai.getPlayer(playerUUID);
  ```
  - 이 방법을 통해 특정 플레이어의 'IsekaiPlayer' 객체를 얻을 수 있다. 이 객체는 이후 모든 플레이어 관련 작업에 사용된다.

  
### 플레이어 역할 관리
플레이어의 역할을 확인하거나 설정할 수 있다. 역할은 서버 내에서 플레이어가 수행하는 기능이나 권한을 나타낸다.

- 플레이어의 역할 가져오기
  ```java
  IsekaiPlayer.Role role = player.getRole();
  System.out.println("현재 역할: " + role.getDisplay());
  ```
  - 플레이어의 현재 역할을 가져올 수 있다. 역할은 'USER', 'MODERATOR', 'OBSERVER', 'PRIEST' 중 하나일 수 있다.


- 플레이어의 역할 설정
  ```java
  player.setRole(IsekaiPlayer.Role.MODERATOR);
  ```
  - 플레이어의 역할을 설정할 수 있다. 이 방법은 플레이어에게 새로운 권한을 부여하거나 기존 권한을 변경할 때 사용된다.
  
### 플레이어 닉네임 관리
플레이어의 닉네임을 확인하거나 설정할 수 있다.

- 플레이어의 닉네임 설정
  ```java
  player.setName(Component.text("새로운 닉네임"));
  ```
  - 플레이어의 닉네임을 설정한다. 이 닉네임은 게임 내에서 다른 플레이어들에게 표시된다.


- 플레이어의 닉네임 가져오기
  ```java
  Component name = player.getName();
  System.out.println("플레이어의 닉네임: " + name);
  ```
  - 플레이어의 현재 닉네임을 받아온다.
  
### 플레이어 접속 상태 관리
플레이어의 접속 상태를 확인하고, 접속 여부에 따라 특정 작업을 수행할 수 있다.

- 플레이어의 첫 접속 여부 확인
  ```java
  boolean hasPlayedBefore = player.hasPlayedBefore();
  System.out.println("이전에 접속한 적이 있나요? " + (hasPlayedBefore ? "예" : "아니요"));
  ```
  - 플레이어가 이전에 서버에 접속한 적이 있는지 확인할 수 있다.


- 플레이어의 첫 접속 상태 설정
  ```java
  player.setPlayedBefore(true);
  ```
  - 플레이어의 첫 접속 상태를 설정할 수 있다. 이를 통해 플레이어가 처음 접속했을 때의 이벤트를 처리할 수 있다.
  
### 플레이어 버프 효과 관리
플레이어에게 특정 버프를 부여하거나 제거할 수 있다.

- 플레이어에게 버프 효과 부여
  ```java
  player.applyEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));
  ```
  - 플레이어에게 특정 포션 효과를 부여한다. 이 예제에서는 30초 동안 1레벨 신속 효과를 부여한다.

- 플레이어에게 부여된 버프 효과 제거
  ```java
  player.removeEffect(PotionEffectType.SPEED);
  ```
  - 플레이어에게 부여된 특정 포션 효과를 제거할 수 있다.

- 플레이어에게 부여된 모든 버프 효과 제거
  ```java
  player.clearEffect();
  ```
  - 플레이어에게 부여된 모든 포션 효과를 제거한다.
  
### 플레이어 인벤토리 관리
플레이어의 인벤토리를 관리할 수 있다. 인벤토리에 아이템을 추가하거나 제거하고, 인벤토리의 모든 아이템을 조회할 수 있다.

- 플레이어의 인벤토리 가져오기
  ```java
  IsekaiInventory inventory = player.getInventory();
  ```
  - 플레이어의 인벤토리 객체를 기져온다. 이를 통해서 아이템을 추가, 제거, 조회할 수 있다.


- 플레이어의 인벤토리에서 특정 아이템 개수 확인
  ```java
  IsekaiItem item = IsekaiItem.of(Material.DIAMOND_SWORD);
  int itemCount = inventory.getItemAmount(item);
  System.out.println("다이아몬드 검의 개수: " + itemCount);
  ```
  - 플레이어의 인벤토리에서 특정 아이템의 개수를 확인할 수 있다.


- 플레이어의 인벤토리에 아이템 추가
  ```java
  inventory.addItem(IsekaiItem.of(Material.DIAMOND_SWORD));
  ```
  - 플레이어의 인벤토리에 아이템을 추가할 수 있다.


- 플레이어의 인벤토리에서 아이템 제거
  ```java
  inventory.removeItem(IsekaiItem.of(Material.DIAMOND_SWORD));
  ```
  - 플레이어의 인벤토리에서 아이템을 제거할 수 있다.

### 플레이어 재화 관리
플레이어의 재화 상태를 조회하고, 재화를 추가하거나 제거할 수 있다.

- 플레이어의 재화 상태 조회
  ```java
  IsekaiCurrency currency = player.getAccount().getCurrency(CoinCurrency.class);
  double balance = currency.get();
  System.out.println("플레이어의 잔액: " + balance);
  ```
  - 플레이어가 보유한 특정 재화의 잔액을 확인할 수 있다.


- 플레이어에게 재화 추가
  ```java
  currency.add(100.0);
  ```
  - 플레이어에게 특정 수량의 재화를 추가할 수 있다.


- 플레이어의 재화 제거
  ```java
  currency.remove(50.0);
  ```
  - 플레이어가 보유한 재화를 특정 수량만큼 제거할 수 있다.

### 플레이어 스테이터스 관리
플레이어의 스테이터스를 조회하고, 경험치 또는 레벨을 추가할 수 있다.

- 특정 스테이터스 조회
  ```java
  IsekaiAttribute strength = player.getStatus().getAttribute(StrengthAttribute.class);
  ```
  - 플레이어의 특정 스테이터스를 조회할 수 있다.


- 스테이터스의 경험치 및 레벨 조회
  ```java
  double currentExp = strength.getExp();
  int currentLevel = strength.getLevel();
  System.out.println("현재 경험치: " + currentExp + ", 현재 레벨: " + currentLevel);
  ```
  - 특정 스테이터스의 현재 경험치와 레벨을 조회할 수 있다.


- 스테이터스의 경험치 및 레벨 추가
  ```java
  strength.addExp(100.0);
  strength.addLevel(1);
  ```
  - 특정 스테이터스의 경험치와 레벨을 추가할 수 있다.

### 플레이어 후원 정보 관리
플레이어가 받은 후원 정보를 조회하고, 후원 계정을 설정할 수 있다.
- 후원 계정 설정
  ```java
  player.getDonation().setChzzkAccount("player_account_id");
  ```
  - 플레이어의 후원 계정을 설정할 수 있다. 이 계정은 치지직 플랫폼과 연동된다.


- 후원 계정 정보 조회
  ```java
  String accountId = player.getDonation().getChzzkAccount();
  System.out.println("후원 계정 ID: " + accountId);
  ```
  - 설정된 후원 계정 정보를 조회할 수 있다.


- 후원 받은 금액 조회
  ```java
  int amount = player.getDonation().getAmount();
  System.out.println("받은 후원 금액: " + amount);
  ```
  - 플레이어가 받은 후원 금액을 조회할 수 있다.

### 플레이어 지역 정보 관리
플레이어가 현재 위치한 지역의 정보를 조회할 수 있다.

- 플레이어가 위치한 지역 정보 조회
  ```java
  Optional<IsekaiArea> area = player.getArea();
  area.ifPresent(a -> System.out.println("플레이어가 위치한 지역: " + a.getName()));
  ```
  - 플레이어가 현재 위치한 지역의 정보를 조회할 수 있다. 지역이 설정되어 있지 않으면 'Optional.empty()'를 반환한다.
  
## 사용 시 주의사항
- 후원 계정이 올바르게 설정되어 있는지 확인이 필요하다. 해당 설정이 잘못 되어있다면 후원 처리가 제대로 이루어지지 않을 확률이 높다.

## 예제 코드
```java
// 플레이어 인스턴스 가져오기
UUID playerUUID = UUID.fromString("player-uuid-string");
IsekaiPlayer player = Isekai.getPlayer(playerUUID);

// 플레이어 역할 설정 및 조회
player.setRole(IsekaiPlayer.Role.MODERATOR);
IsekaiPlayer.Role role = player.getRole();
System.out.println("현재 역할: " + role.getDisplay());

// 플레이어 닉네임 설정 및 조회
        player.setName(Component.text("AdminUser"));
Component name = player.getName();
System.out.println("플레이어의 닉네임: " + name);

// 플레이어가 이전에 접속한 적이 있는지 확인
if (!player.hasPlayedBefore()) {
        player.setPlayedBefore(true);
    System.out.println("첫 접속으로 설정되었습니다.");
}

// 플레이어에게 버프 효과 부여
        player.applyEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1));

// 플레이어의 인벤토리 관리
IsekaiInventory inventory = player.getInventory();
inventory.addItem(IsekaiItem.of(Material.DIAMOND_SWORD));

// 플레이어의 재화 관리
IsekaiCurrency currency = player.getAccount().getCurrency(CoinCurrency.class);
currency.add(100.0);
System.out.println("플레이어의 잔액: " + currency.get());

// 플레이어의 스탯 관리
IsekaiAttribute strength = player.getStatus().getAttribute(StrengthAttribute.class);
strength.addExp(100.0);
System.out.println("현재 경험치: " + strength.getExp());

// 플레이어의 후원 정보 조회
String accountId = player.getDonation().getChzzkAccount();
int donatedAmount = player.getDonation().getAmount();
System.out.println("후원 계정 ID: " + accountId);
System.out.println("받은 후원 금액: " + donatedAmount);

// 플레이어가 위치한 지역 정보 조회
Optional<IsekaiArea> area = player.getArea();
area.ifPresent(a -> System.out.println("플레이어가 위치한 지역: " + a.getName()));
```

# IsekaiInventory 관리 API 사용 설명서

## 개요
'IsekaiInventory 관리 API'는 Minecraft 서버에서 플레이어의 인벤토리를 관리하는 기능을 제공한다.
이 API를 사용하면 인벤토리의 아이템을 추가하거나 제거하고, 인벤토리의 상태를 조회하며, 후원 전용 인벤토리도 관리할 수 있다.

## 각 기능의 사용 방법 및 상세 설명

### 아이템의 존재 여부 확인
플레이어의 인벤토리에 특정 아이템이 존재하는지 확인할 수 있다.

- 아이템 ID를 기반으로 아이템 존재 여부 확인
  ```java
  IsekaiItem item = IsekaiItem.of("item_id");
  boolean hasItem = inventory.hasItem(item);
  ```
  - 'IsekaiItem' 객체를 사용하여 인벤토리에 해당 아이템이 존재하는지 확인할 수 있다.


- Material을 기반으로 아이템 존재 여부 확인
  ```java
  IsekaiItem item = IsekaiItem.of(Material.DIAMOND_SWORD);
  boolean hasItem = inventory.hasItem(item);
  ```
  - Material을 사용하여 인벤토리에 특정 바닐라 아이템이 있는지 확인할 수 있다.
  
### 아이템 추가
플레이어의 인벤토리에 아이템을 추가할 수 있다.

- 인벤토리에 아이템 추가
  ```java
  IsekaiItem item = IsekaiItem.of("item_id");
  inventory.addItem(item);
  ```
  - 'IsekaiItem' 객체를 인벤토리에 추가할 수 있다. 이 메소드는 아이템을 플레이어의 인벤토리에 직접 추가한다.
  
### 아이템 제거
플레이어의 인벤토리에서 특정 아이템을 제거할 수 있다.

- 아이템 ID를 기반으로 인벤토리에서 아이템 제거
  ```java
  IsekaiItem item = IsekaiItem.of("item_id");
  inventory.removeItem(item);
  ```
  - 'IsekaiItem' 객체를 사용하여 인벤토리에서 해당 아이템을 제거할 수 있다.


- Material을 기반으로 인벤토리에서 아이템 제거
  ```java
  IsekaiItem item = IsekaiItem.of(Material.DIAMOND_SWORD);
  inventory.removeItem(item);
  ```
  - Material을 사용하여 인벤토리에서 특정 바닐라 아이템을 제거할 수 있다.
  
### 인벤토리 상태 확인
플레이어의 인벤토리에 남은 공간이 있는지 확인할 수 있다.

- 인벤토리에 남은 공간 확인
  ```java
  boolean hasSpace = inventory.hasEmptySpace();
  ```
  - 인벤토리에 빈 공간이 있는지 확인할 수 있다. 빈 공간이 있다면 'true', 없다면 'false'를 반환한다.

### 후원 전용 인벤토리 관리
플레이어의 후원 전용 인벤토리에 아이템을 추가하거나 수령할 수 있다.

- 후원 전용 인벤토리에 아이템 추가
  ```java
  IsekaiMail mail = IsekaiMail.of(item);
  inventory.getMailBox().addLast(mail);
  ```
  - 'IsekaiMail' 객체를 후원 전용 인벤토리에 추가할 수 있다. 'addLast'는 인벤토리의 마지막에 아이템을 추가한다.


- 후원 전용 인벤토리에서 특정 아이템 수령
  ```java
  inventory.getMailBox().receiveMail(0);
  ```
  - 후원 전용 인벤토리에서 특정 칸에 있는 아이템을 수령할 수 있다.


- 후원 전용 인벤토리에서 모든 아이템 수령
  ```java
  inventory.getMailBox().receiveMails();
  ```
  - 후원 전용 인벤토리에 있는 모든 아이템을 수령할 수 있다.

### 인벤토리 전체 조회 및 비우기
플레이어의 인벤토리에 있는 모든 아이템을 조회하거나 비울 수 있다.

- 인벤토리의 모든 아이템 조회
  ```java
  List<IsekaiItem> items = inventory.getContents();
  items.forEach(item -> System.out.println(item.getName()));
  ```
  - 인벤토리에 있는 모든 아이템을 리스트로 가져올 수 있다.


- 인벤토리 비우기
  ```java
  inventory.clear();
  ```
  - 인벤토리의 모든 아이템을 제거하여 비울 수 있다.
  
## 사용 시 주의사항
- 인벤토리의 아이템을 추가하거나 제거할 때, 플레이어가 온라인 상태인지 확인해야 한다.
  플레이어가 오프라인일 경우, 후원 전용 인벤토리를 통해 아이템을 관리하는 것이 좋다.
- 인벤토리의 아이템 스택이 서버 설정과 일치하도록 관리해야 한다. 너무 큰 스택은 인벤토리 관리에 문제를 일으킬 수도 있다.

## 예제 코드
```java
// 인벤토리에서 아이템 존재 여부 확인
IsekaiItem sword = IsekaiItem.of(Material.DIAMOND_SWORD);
boolean hasSword = inventory.hasItem(sword);
System.out.println("다이아몬드 검 보유: " + (hasSword ? "예" : "아니요"));

// 인벤토리에 아이템 추가
IsekaiItem newItem = IsekaiItem.of("my_sword");
inventory.addItem(newItem);
System.out.println("아이템이 추가되었습니다.");

// 인벤토리에서 아이템 제거
inventory.removeItem(newItem);
System.out.println("아이템이 제거되었습니다.");

// 인벤토리 공간 확인 및 후원 전용 인벤토리로 아이템 전송
if (!inventory.hasEmptySpace()) {
    inventory.getMailBox().addFirst(IsekaiMail.of(newItem));
    System.out.println("공간이 부족하여 아이템이 후원 전용 인벤토리로 전송되었습니다.");
}

// 인벤토리의 모든 아이템 조회
List<IsekaiItem> items = inventory.getContents();
System.out.println("현재 인벤토리 아이템:");
items.forEach(item -> System.out.println("- " + item.getName()));

// 후원 전용 인벤토리에서 아이템 수령
inventory.getMailBox().receiveMails();
System.out.println("후원 전용 인벤토리의 모든 아이템이 수령되었습니다.");
```

# IsekaiCurrency 관리 API 사용 설명서

## 개요
'IsekaiCurrency 관리 API'는 Minecraft 서버에서 플레이어가 보유한 다양한 재화를 관리하는 기능을 제공한다.
이 API를 사용하면 플레이어의 재화를 조회하고, 추가하거나 제거하며, 데이터베이스와 연동하여 재화 상태를 관리할 수 있ek.

## 각 기능의 사용 방법 및 상세 설명

### 재화 조회 및 관리
플레이어가 보유한 특정 유형의 재화를 조회하고, 재화를 추가하거나 제거할 수 있다.

- 특정 재화 조회
  ```java
  IsekaiCurrency currency = account.getCurrency(CoinCurrency.class);
  double balance = currency.get();
  System.out.println("플레이어의 잔액: " + balance);
  ```
  - 특정 재화를 조회하여 플레이어의 현재 잔액을 확인할 수 있다.


- 플레이어가 특정 재화를 보유하고 있는지 확인
  ```java
  boolean hasEnough = currency.has(50.0);
  System.out.println("재화를 충분히 보유하고 있나요? " + (hasEnough ? "예" : "아니요"));
  ```
  - 플레이어가 특정 양 이상의 재화를 보유하고 있는지 확인할 수 있다.


- 재화 추가, 제거, 설정
  ```java
  currency.add(100.0);
  System.out.println("100 단위의 재화가 추가되었습니다.");
  
  currency.remove(50.0);
  System.out.println("50 단위의 재화가 제거되었습니다.");
  
  currency.set(200.0);
  System.out.println("재화의 잔액이 200으로 설정되었습니다.");
  ```
  - 플레이어에게 재화를 추가하거나 제거 또는 설정할 수 있다.
  
### 데이터베이스와 연동하여 재화 관리
플레이어의 재화 상태를 데이터베이스에 저장하거나 불러올 수 있다.

- 재화 상태 데이터베이스에 저장
  ```java
  account.save();
  System.out.println("재화 상태가 데이터베이스에 저장되었습니다.");
  ```
  - 현재 재화 상태를 데이터베이스에 저장할 수 있다. 이는 서버 종료 시 또는 중요한 변경 사항 후에 사용된다.

### 데이터 기반 재화 관리
데이터 파일에 저장된 재화를 불러오고, 재화를 추가하거나 제거할 수 있다.

- 데이터 파일에서 재화 불러오기
  ```java
  IsekaiCurrency dataCurrency = account.getCurrency(DataCurrency.class);
  double value = dataCurrency.get();
  System.out.println("불러온 재화의 잔액: " + value);
  ```
  - 데이터 파일에서 재화를 불러와 현재 잔액을 확인할 수 있다.


- 재화 상태 데이터베이스에 저장
  ```java
  dataCurrency.save();
  System.out.println("데이터 기반 재화가 저장되었습니다.");
  ```
  - 데이터 기반 재화를 데이터베이스에 저장할 수 있다.

### 아이템 기반 재화 관리
플레이어의 인벤토리에 있는 아이템을 기반으로 재화를 관리할 수 있다.

- 아이템 기반 재화 조회
  ```java
  IsekaiCurrency itemCurrency = account.getCurrency(ItemCurrency.class);
  double value = itemCurrency.get();
  System.out.println("아이템 기반 재화의 잔액: " + value);
  ```
  - 인벤토리에 있는 아이템을 기반으로 재화를 조회할 수 있다.


- 아이템 기반 재화 추가, 제거
  ```java
  itemCurrency.add(10.0);
  System.out.println("아이템 기반 재화가 10 단위 추가되었습니다.");
  
  itemCurrency.remove(5.0);
  System.out.println("아이템 기반 재화가 5 단위 제거되었습니다.");
  ```
  - 아이템을 기반으로 재화를 추가하거나 제거할 수 있다.
  
## 사용 시 주의사항
- 재화를 추가하거나 제거할 때, 해당 재화가 유효한지 확인해야 한다.
- 재화의 상태를 변경한 후에는 반드시 'save()' 메소드를 호출하여 데이터베이스에 저장해야 한다. 그렇지 않으면 변경 사항이 서버 재시작 시 유실될 수 있다.
- 아이템을 기반으로 재화를 관리할 때는 인벤토리를 주기적으로 확인하여 아이템과 재화가 일치하도록 유지해야 한다.

## 예제 코드
```java
// 특정 재화 조회 및 확인
IsekaiCurrency coinCurrency = account.getCurrency(CoinCurrency.class);
double coinBalance = coinCurrency.get();
System.out.println("플레이어의 잔액: " + coinBalance);

// 재화 추가 및 제거
coinCurrency.add(100.0);
System.out.println("100 단위의 재화가 추가되었습니다.");

coinCurrency.remove(50.0);
System.out.println("50 단위의 재화가 제거되었습니다.");

// 재화의 양을 설정
coinCurrency.set(200.0);
System.out.println("잔액이 200으로 설정되었습니다.");

// 재화 상태를 데이터베이스에 저장
account.save();
System.out.println("재화 상태가 데이터베이스에 저장되었습니다.");

// 데이터 기반 재화 관리
IsekaiCurrency dataCurrency = account.getCurrency(DataCurrency.class);
double dataValue = dataCurrency.get();
System.out.println("데이터 기반 재화의 잔액: " + dataValue);
dataCurrency.save();
System.out.println("데이터 기반 재화가 저장되었습니다.");

// 아이템 기반 재화 관리
IsekaiCurrency itemCurrency = account.getCurrency(ItemCurrency.class);
itemCurrency.add(10.0); // 인벤토리 체크 후 추가
System.out.println("아이템 기반 재화가 10 단위 추가되었습니다.");
itemCurrency.remove(5.0); // 인벤토리 체크 후 제거
System.out.println("아이템 기반 재화가 5 단위 제거되었습니다.");
```

# IsekaiStatus 관리 API 사용 설명서

## 개요
'IsekaiStatus 관리 API'는 Minecraft 서버에서 플레이어의 스탯을 관리하는 기능을 제공한다.
스탯 ID를 기반으로 플레이어의 특정 스탯을 조회하고, 경험치와 레벨을 수정할 수 있다.
또한, 각 레벨별로 필요한 경험치 데이터를 조회하고, 레벨 업 이벤트를 처리할 수 있는 시스템을 제공한다.

## 각 기능의 사용 방법 및 상세 설명

### 스테이터스 조회 및 관리
플레이어의 특정 스테이터스를 조회하고, 해당 스테이터스의 경험치와 레벨을 관리할 수 있다.

- 특정 스테이터스 조회
  ```java
  IsekaiAttribute strength = status.getAttribute(StrengthAttribute.class);
  ```
  - 플레이어의 특정 스테이터스를 조회할 수 있다.


- 스테이터스의 현재 경험치와 레벨 조회
  ```java
  double currentExp = strength.getExp();
  int currentLevel = strength.getLevel();
  System.out.println("현재 경험치: " + currentExp);
  System.out.println("현재 레벨: " + currentLevel);
  ```
  - 플레이어의 현재 스테이터스 경험치와 레벨을 확인할 수 있다.
  
### 스테이터스 수정
플레이어의 스테이터스 경험치와 레벨을 설정하거나 추가, 제거할 수 있다.

- 스테이터스 경험치와 레벨을 설정, 추가, 제거
  ```java
  strength.setExp(500.0);
  System.out.println("경험치가 500으로 설정되었습니다.");
  
  strength.setLevel(10);
  System.out.println("레벨이 10으로 설정되었습니다.");
  
  strength.addExp(100.0);
  System.out.println("100 경험치가 추가되었습니다.");
  
  strength.addLevel(1);
  System.out.println("레벨이 1 증가했습니다.");
  
  strength.removeExp(50.0);
  System.out.println("50 경험치가 제거되었습니다.");
  
  strength.removeLevel(1);
  System.out.println("레벨이 1 감소했습니다.");
  ```
  - 스테이터스의 레벨과 경험치를 특정 수치만큼 수정할 수 있다.

### 레벨별 필요 경험치 조회
특정 레벨에 도달하기 위해 필요한 경험치를 조회할 수 있다.

- 특정 레벨에 도달하기 위해 필요한 경험치 조회
  ```java
  double expRequired = strength.getExpRequired(10);
  System.out.println("레벨 10에 도달하기 위해 필요한 경험치: " + expRequired);
  ```
  - 특정 레벨에 도달하기 위해 필요한 경험치 양을 확인할 수 있다.
  
### 스테이터스 데이터 저장
플레이어의 스테이터스 정보를 데이터베이스에 저장할 수 있다.

- 스테이터스  데이터 저장
  ```java
  status.save();
  strength.ssave();
  System.out.println("스탯 데이터가 저장되었습니다.");
  ```
  - 현재 스테이터스 상태를 데이터베이스에 저장할 수 있다. 이는 서버 종료 시 또는 중요한 변경 사항 후에 적용해야 한다.
  
### 레벨 업 이벤트 처리
스테이터스의 레벨이 변경될 때 레벨 업 이벤트를 처리할 수 있다.

- 레벨 업 이벤트 처리
  ```java
  int prevLevel = strength.getLevel();
  strength.addLevel(1);
  int newLevel = strength.getLevel();

  if (newLevel > prevLevel) {
  System.out.println("레벨 업! 새로운 레벨: " + newLevel);
  // 추가적인 레벨 업 이벤트 처리 로직
  }
  ```
  - 스테이터스의 레벨이 변경될 때 레벨 업 이벤트를 처리할 수 있다.
  
## 사용 시 주의사항
- 경험치를 수정할 때, 레벨이 자동으로 변경될 수 있으므로 경험치와 레벨의 상호작용에 유의해야 한다.
- 스탯 데이터를 변경한 후에는 반드시 'save()' 메소드를 호출하여 변경 사항을 저장해야 한다.

## 예제 코드
```java
// 특정 스탯 조회 및 수정
IsekaiAttribute strength = status.getAttribute(StrengthAttribute.class);

// 경험치와 레벨 확인
double currentExp = strength.getExp();
int currentLevel = strength.getLevel();
System.out.println("현재 경험치: " + currentExp);
System.out.println("현재 레벨: " + currentLevel);

// 경험치와 레벨 설정
strength.setExp(500.0);
strength.setLevel(10);
System.out.println("경험치와 레벨이 설정되었습니다.");

// 경험치와 레벨 추가
strength.addExp(100.0);
strength.addLevel(1);
System.out.println("경험치와 레벨이 추가되었습니다.");

// 특정 레벨에 필요한 경험치 조회
double expRequired = strength.getExpRequired(10);
System.out.println("레벨 10에 도달하기 위해 필요한 경험치: " + expRequired);

// 스탯 데이터 저장
strength.save();
status.save();
System.out.println("스탯 데이터가 저장되었습니다.");

// 레벨 업 이벤트 처리
int prevLevel = currentLevel;
strength.addLevel(1);
int newLevel = strength.getLevel();
if (newLevel > prevLevel) {
        System.out.println("레벨 업! 새로운 레벨: " + newLevel);
// 레벨 업 이벤트 관련 추가 로직
}
```

# IsekaiArea 관리 API 사용 설명서

## 개요
'IsekaiArea 관리 API'는 Minecraft 서버에서 특정 범위를 지역으로 설정하고 관리하는 기능을 제공한다.
ID를 기반으로 지역을 관리할 수 있으며, 플레이어가 특정 범위 내에 있는지 판단하는 기능을 포함한다.
'BoundingBox' 클래스는 지역의 범위를 정의하고, 이를 기반으로 플레이어의 위치를 판별한다.

## 각 기능의 사용 방법 및 상세 설명

### 지역 생성 및 관리
서버에서 관리할 지역을 생성하고, 해당 지역의 속성을 조회할 수 있다.

- 두 개의 'Location'을 사용하여 'IsekaiBoundingBox' 생성
  ```java
  Location loc1 = new Location(world, 100, 64, 100);
  Location loc2 = new Location(world, 150, 70, 150);
  IsekaiBoundingBox box = IsekaiBoundingBox.of(loc1, loc2);
  ```
  - 두 개의 좌표를 지정하여 'IsekaiBoundingBox'를 생성할 수 있다. 이 박스는 지역의 범위를 나타내며, 이후 지역 내에 플레이어가 있는지 여부를 판단하는 데 사용된다.


- 'IsekaiArea'를 생성하여 지역 관리
  ```java
  IsekaiArea area = IsekaiArea.of("area_01", "마을 광장", box);
  ```
  - 생성된 'IsekaiBoundingBox'를 사용하여 새로운 지역을 생성할 수 있다. 지역 ID와 이름을 지정하여 생성된 지역을 관리한다.


- 지역의 'BoundingBox', ID, 이름, 소유자 조회
  ```java
  IsekaiBoundingBox areaBox = area.getBoundingBox();
  String areaId = area.getId();
  String areaName = area.getName();
  Optional<IsekaiPlayer> owner = area.getOwner();
  ```
  - 특정 지역의 범위, ID, 이름, 소유자를 조회할 수 있다. 소유자가 없는 경우 'Optional.empty()'를 반환한다.
  
### 지역 내 플레이어의 위치 확인
플레이어가 특정 지역 내에 있는지 확인할 수 있다. 이는 특정 이벤트나 행동을 지역 내에서만 허용하거나 제한할 때 유용하다.

- 플레이어가 특정 지역 내에 위치하는지 확인
  ```java
  Location playerLocation = player.getLocation();
  boolean isInArea = area.getBoundingBox().contains(playerLocation);
  System.out.println("플레이어가 지역 내에 있습니까? " + (isInArea ? "예" : "아니요"));
  ```
  - 플레이어의 현재 위치가 특정 지역 내에 있는지 확인할 수 있다.
  
### 지역 목록 관리
서버에 등록된 모든 지역을 관리하고, ID나 위치를 기준으로 지역을 조회할 수 있다.

- 서버에 등록된 모든 지역 로드
  ```java
  IsekaiAreaManager areaManager = Isekai.getAreaManager();
  areaManager.load();
  System.out.println("모든 지역이 로드되었습니다.");
  ```
  - 서버에 등록된 모든 지역을 로드하여 메모리에 올린다.


- ID를 기반으로 특정 지역 조회
  ```java
  Optional<IsekaiArea> optionalArea = areaManager.getAreaById("area_01");
  optionalArea.ifPresent(a -> System.out.println("지역 이름: " + a.getName()));
  ```
  - 지역 ID를 사용하여 특정 지역을 조회할 수 있다. 지역이 존재하는 경우 해당 지역의 이름을 출력한다.


- 특정 위치에 존재하는 모든 지역 조회
  ```java
  Set<IsekaiArea> areasAtLocation = areaManager.getAreasByLocation(playerLocation);
  areasAtLocation.forEach(a -> System.out.println("해당 위치에 존재하는 지역: " + a.getName()));
  ```
  - 특정 위치에 존재하는 모든 지역을 조회할 수 있다. 이는 하나의 위치가 여러 지역에 속할 수 있을 때 유용하다.

### 지역 저장 및 데이터베이스 연동
새롭게 생성한 지역이나 변경된 지역 정보를 데이터베이스에 저장하여 서버 재시작 시에도 정보가 유지되도록 한다.

- 지역 정보 저장
  ```java
  areaManager.save(area);
  System.out.println("지역 정보가 저장되었습니다.");
  ```
  - 새로운 지역을 추가하거나, 기존 지역의 정보를 변경한 후에는 반드시 'save()' 메소드를 호출하여 데이터를 저장해야 한다.

## 사용 시 주의사항
- 매우 큰 지역을 관리할 때는 성능에 주의해야 한다. 플레이어의 위치를 자주 확인하는 경우, 너무 큰 지역은 서버 성능에 영향을 줄 수 있다.
- 하나의 위치가 여러 지역에 속할 수 있는 경우, 중첩된 지역 간의 우선순위를 관리해야 한다.
- 지역 데이터를 변경한 후, 반드시 저장하여 서버 재시작 후에도 일관된 상태를 유지하도록 해야한다.

## 예제 코드
```java
// 지역 범위 설정
Location loc1 = new Location(world, 100, 64, 100);
Location loc2 = new Location(world, 150, 70, 150);
IsekaiBoundingBox box = IsekaiBoundingBox.of(loc1, loc2);

// 새로운 지역 생성
IsekaiArea area = IsekaiArea.of("area_01", "마을 광장", box);
System.out.println("새로운 지역이 생성되었습니다: " + area.getName());

// 플레이어가 지역 내에 있는지 확인
Location playerLocation = player.getLocation();
boolean isInArea = area.getBoundingBox().contains(playerLocation);
System.out.println("플레이어가 지역 내에 있습니까? " + (isInArea ? "예" : "아니요"));

// 특정 위치에 존재하는 모든 지역 조회
IsekaiAreaManager areaManager = Isekai.getAreaManager();
Set<IsekaiArea> areasAtLocation = areaManager.getAreasByLocation(playerLocation);
areasAtLocation.forEach(a -> System.out.println("해당 위치에 존재하는 지역: " + a.getName()));

// 지역 정보 저장
areaManager.save(area);
System.out.println("지역 정보가 저장되었습니다.");
```

# IsekaiNpc 관리 API 사용 설명서

## 개요
'IsekaiNpc 관리 API'는 Minecraft 서버에서 NPC를 관리하고, 특정 ID를 기반으로 NPC를 조회하거나 설정할 수 있는 기능을 제공한다.
이 API를 통해 NPC의 고유 ID, 이름, 그리고 역할을 조회하고, 특정 NPC가 어떤 타입의 NPC인지 설정할 수 있다.

## 각 기능의 사용 방법 및 상세 설명

### NPC 생성 및 관리
새로운 NPC를 생성하고, 해당 NPC의 속성을 관리할 수 있다.

- NPC 생성
  ```java
  IsekaiNPC npc = IsekaiNPC.of("npc_001", "마을 경비병", IsekaiPlayer.Role.GUARD);
  ```
  - 고유 ID, 이름, 그리고 역할을 지정하여 새로운 NPC를 생성할 수 있다.


- NPC의 고유 ID, 이름, 역할 조회
  ```java
  String npcId = npc.getId();
  String npcName = npc.getName();
  IsekaiPlayer.Role npcRole = npc.getRole();
  
  System.out.println("NPC ID: " + npcId);
  System.out.println("NPC 이름: " + npcName);
  System.out.println("NPC 역할: " + npcRole.getDisplay());
  ```
  - 생성된 NPC의 고유 ID, 이름, 그리고 역할을 조회할 수 있다.

### NPC 역할 관리
NPC의 역할을 설정하거나 변경할 수 있다.

- NPC 역할 설정
  ```java
  npc.setRole(IsekaiPlayer.Role.MERCHANT);
  System.out.println("NPC 역할이 상인으로 설정되었습니다.");
  ```
  - 특정 역할을 NPC에게 설정할 수 있다.


- NPC 역할 변경
  ```java
  npc.setRole(IsekaiPlayer.Role.GUARD);
  System.out.println("NPC 역할이 경비병으로 변경되었습니다.");
  ```
  - NPC의 역할을 변경할 수 있다.
  
### NPC 상호작용
NPC와 상호작용하는 로직을 구현하여 플레이어와의 상호작용을 관리할 수 있다.

- NPC와 대화 이벤트 구현
  ```java
  public void onPlayerInteractWithNPC(IsekaiPlayer player, IsekaiNPC npc) {
    if (npc.getRole() == IsekaiPlayer.Role.MERCHANT) {
        // 상인 NPC와의 대화 처리
        System.out.println(npc.getName() + "이(가) 당신에게 상품을 판매하려 합니다.");
    } else if (npc.getRole() == IsekaiPlayer.Role.GUARD) {
        // 경비병 NPC와의 대화 처리
        System.out.println(npc.getName() + "이(가) 당신을 지켜보고 있습니다.");
    }
  }
  ```
  - 플레이어가 NPC와 상호작용할 때, NPC의 역할에 따라 다른 이벤트를 처리할 수 있다.

### NPC 데이터 저장 및 로드
생성된 NPC의 데이터를 저장하여 서버 재시작 시에도 정보가 유지되도록 하고, 저장된 NPC 데이터를 로드할 수 있다.

- NPC 데이터 저장
  ```java
  npc.save();
  System.out.println("NPC 데이터가 저장되었습니다.");
  ```
  - 생성된 NPC의 상태와 정보를 데이터베이스에 저장할 수 있다. 이는 서버 재시작 시 NPC의 상태를 유지하는 데 필요하다.


- NPC 데이터 로드
  ```java
  IsekaiNPC loadedNPC = IsekaiNPC.load("npc_001");
  System.out.println("NPC 데이터가 로드되었습니다: " + loadedNPC.getName());
  ```
  - 저장된 NPC 데이터를 로드하여 서버 재시작 후에도 동일한 NPC를 사용할 수 있다.

## 사용 시 주의사항
- 동일한 ID를 가진 NPC가 여러 개 존재하지 않도록 관리해야 한다. ID가 중복되면 데이터 충돌이 발생할 수 있다.
- 역할에 따라 NPC의 행동이 달라지도록 설계해야 한다. 역할 변경 시 행동 로직이 변경될 수 있으므로 주의해야 한다.
- NPC의 데이터를 변경한 후에는 반드시 'save()' 메소드를 호출하여 데이터베이스에 저장해야 한다. 그렇지 않으면 변경 사항이 서버 재시작 시 유실될 수 있다.

## 예제 코드
```java
// 새로운 NPC 생성
IsekaiNPC npc = IsekaiNPC.of("npc_001", "마을 경비병", IsekaiPlayer.Role.GUARD);
System.out.println("NPC가 생성되었습니다: " + npc.getName());

// NPC 역할 설정 및 변경
npc.setRole(IsekaiPlayer.Role.MERCHANT);
System.out.println("NPC 역할이 상인으로 설정되었습니다.");

npc.setRole(IsekaiPlayer.Role.GUARD);
System.out.println("NPC 역할이 경비병으로 변경되었습니다.");

// NPC와의 상호작용 처리
public void onPlayerInteractWithNPC(IsekaiPlayer player, IsekaiNPC npc) {
  if (npc.getRole() == IsekaiPlayer.Role.MERCHANT) {
    System.out.println(npc.getName() + "이(가) 당신에게 상품을 판매하려 합니다.");
  } else if (npc.getRole() == IsekaiPlayer.Role.GUARD) {
    System.out.println(npc.getName() + "이(가) 당신을 지켜보고 있습니다.");
  }
}

// NPC 데이터 저장 및 로드
npc.save();
System.out.println("NPC 데이터가 저장되었습니다.");

IsekaiNPC loadedNPC = IsekaiNPC.load("npc_001");
System.out.println("NPC 데이터가 로드되었습니다: " + loadedNPC.getName());
```

# Donation 관리 API 사용 설명서

## 개요
'Donation 관리 API'는 치지직 API와 연동되어, 치지직 채널에서 후원을 받은 양에 따라 Minecraft 서버에서 이를 처리하는 기능을 제공한다.
이 API를 통해 특정 플레이어에게 얼마만큼의 후원이 이루어졌는지 확인하고, 후원 관련 이벤트를 호출할 수 있다.

## 각 기능의 사용 방법 및 상세 설명

### 후원 계정 설정 및 관리
플레이어의 후원 계정을 설정하고, 해당 계정의 정보를 관리할 수 있다.

- 후원 계정 설정
  ```java
  IsekaiDonation donation = player.getDonation();
  donation.setChzzkAccount("player_account_id");
  System.out.println("후원 계정이 설정되었습니다.");
  ```
  - 플레이어의 후원 계정을 설정할 수 있다.


- 후원 계정 정보 조회
  ```java
  String accountId = donation.getChzzkAccount();
  System.out.println("후원 계정 ID: " + accountId);
  ```
  - 설정된 후원 계정 ID를 조회할 수 있다.
  
### 후원 금액 조회
플레이어가 받은 후원 금액을 조회하고, 이를 바탕으로 서버 내에서 다양한 이벤트를 처리할 수 있다.

- 후원된 금액 조회
  ```java
  int amount = donation.getAmount();
  System.out.println("후원된 금액: " + amount + "원");
  ```
  - 플레이어가 현재까지 받은 후원 금액을 조회할 수 있다.
  
### 후원 이벤트 처리
후원이 발생했을 때, 이를 기반으로 다양한 서버 내 이벤트를 처리할 수 있다.

- 후원 이벤트 처리
  ```java
  public void onDonationReceived(IsekaiPlayer player, int amount) {
    IsekaiDonation donation = player.getDonation();
    donation.addAmount(amount); // 후원 금액 추가
    donation.save(); // 후원 정보를 저장

    // 후원 금액에 따른 보상 지급
    if (amount == 10000) {
        player.getInventory().addItem(IsekaiItem.of(Material.DIAMOND, 10)); // 다이아몬드 5개 지급
        System.out.println(player.getName() + "님이 후원을 통해 다이아몬드 10개를 받았습니다!");
    }
  }
  ```
  - 후원 금액에 따라 플레이어에게 보상을 지급하거나, 특정 이벤트를 처리할 수 있다.

### 데이터 저장 및 로드
후원 정보를 데이터베이스에 저장하여 서버 재시작 시에도 후원 정보를 유지하고, 기존 후원 데이터를 로드할 수 있다.

- 후원 정보 저장 및 로드
  ```java
  donation.save();
  System.out.println("후원 정보가 저장되었습니다.");
  
  donation.load();
  System.out.println("후원 정보가 로드되었습니다.");
  ```
  - 후원 정보를 데이터베이스에 저장하여 서버가 재시작되더라도 후원 내역이 유지되도록하며, 서버가 시작될 때 해당 정보를 받아와 내역이 이어지도록 한다.
  
## 사용 시 주의사항
- 후원 계정 설정이 잘못되었을 때에는 후원이 제대로 처리되지 않을 수 있다.
- 후원 이벤트를 처리할 때, 보상 지급이 중복되지 않도록 주의해야 한다.

## 예제 코드
```java
// 후원 계정 설정
IsekaiDonation donation = player.getDonation();
donation.setChzzkAccount("player123");
System.out.println("후원 계정이 설정되었습니다: " + donation.getChzzkAccount());

// 후원된 금액 조회
int donatedAmount = donation.getAmount();
System.out.println("후원된 금액: " + donatedAmount + "원");

// 후원 이벤트 처리
public void onDonationReceived(IsekaiPlayer player, int amount) {
    IsekaiDonation donation = player.getDonation();
    donation.addAmount(amount); // 후원 금액 추가
    donation.save(); // 후원 정보를 저장

  // 후원 금액에 따른 보상 지급
    if (amount >= 10000) {
        player.getInventory().addItem(IsekaiItem.of(Material.DIAMOND, 5)); // 다이아몬드 5개 지급
        System.out.println(player.getName() + "님이 후원을 통해 다이아몬드 5개를 받았습니다!");
    }
}

// 후원 정보 저장 및 로드
donation.save();
System.out.println("후원 정보가 저장되었습니다.");

donation.load();
System.out.println("후원 정보가 로드되었습니다.");
```

# Config / Data 파일 관리 API 사용 설명서

## 개요
'Config / Data 파일 관리 API'는 Minecraft 서버에서 JSON 기반의 설정 및 데이터 파일을 관리하는 기능을 제공한다.
이 API는 파일에서 데이터를 불러오고, 데이터를 수정한 후 파일에 다시 저장할 수 있도록 돕는다.
또한, 데이터를 다시 특정 형식으로 변환하거나, 데이터를 처리하는 방법을 커스터마이징 할 수 있다.

## 각 기능의 사용 방법 및 상세 설명

### Configurator 생성 및 사용
설정 파일 또는 데이터 파일을 관리할 'Configurator'를 생성하고, 이를 통해 데이터를 저장하고 불러올 수 있다.

- Configurator 생성
  ```java
  JsonConfigurator<MyClass> configurator = new JsonConfigurator<>("path/to/file.json", MyClass.class);
  ```
  - 'JsonConfigurator'를 사용하여 JSON 파일을 관리할 수 있다. 생성 시 파일 경로와 데이터를 저장할 클래스 타입을 지정해야 한다.


- 데이터 로드
  ```java
  MyClass dataObject = configurator.load();
  System.out.println("데이터가 로드되었습니다: " + dataObject.toString());
  ```
  - 지정된 경로에서 데이터를 로드하여, 해당 데이터를 객체로 변환한다. 로드된 데이터는 이후 서버 내에서 사용 가능하다.


- 데이터 저장
  ```java
  configurator.save(dataObject);
  System.out.println("데이터가 저장되었습니다.");
  ```
  - 객체 데이터를 JSON 형식으로 지정된 경로에 저장한다. 이를 통해 서버 종료 후에도 데이터를 유지할 수 있다.
  
### GSON 설정 관리
'Gson' 객체를 사용하여 데이터를 JSON 형식으로 직렬화하거나, JSON 데이터를 객체로 역직렬화할 수 있다.
커스텀 'TypeAdapter'를 추가하여 특정 데이터 타입의 직렬화/역직렬화 방식을 정의할 수 있다.

- 사용자 정의 설정이 적용된 Gson 인스턴스 가져오기
  ```java
  Gson gson = Isekai.getIsekaiGson().getGson();
  ```
  - 사용자 정의 설정이 포함된 'Gson' 인스턴스를 가져와 데이터를 변환할 때 사용할 수 있다.
  이 인스턴스는 기본적인 JSON 직렬화/역직렬화 이외에도 커스텀 설정을 포함한다.


- TypeAdapter 추가
  ```java
  Isekai.getIsekaiGson().addTypeAdapter(MyClass.class, new MyClassAdapter());
  System.out.println("MyClass에 대한 TypeAdapter가 추가되었습니다.");
  ```
  - 특정 데이터 타입을 직렬화/역직렬화할 때 사용할 'TypeAdapter'를 추가할 수 있다. 이를 통해 데이터를 원하는 형식으로 변환할 수 있다.

### JSON 파일 관리
설정 파일이나 데이터 파일을 JSON 형식으로 저장하고 불러와서, 서버에서 필요한 데이터를 관리할 수 있다.

- JSON 데이터 직렬화
  ```java
  String jsonData = gson.toJson(dataObject);
  System.out.println("JSON 데이터: " + jsonData);
  ```
  - 객체 데이터를 JSON 문자열로 직렬화한다. 직렬화된 데이터는 파일에 저장하거나, 네트워크를 통해 전송할 수 있다.


- JSON 데이터 역직렬화
  ```java
  MyClass newDataObject = gson.fromJson(jsonData, MyClass.class);
  System.out.println("역직렬화된 데이터: " + newDataObject.toString());
  ```
  - JSON 문자열을 객체로 역직렬화하여, 서버 내에서 다시 사용할 수 있다.

### 데이터 일관성 유지 및 오류 처리
데이터를 저장하고 불러오는 과정에서 발생할 수 있는 오류를 처리하고, 데이터 일관성을 유지하는 방법에 대한 설명이다.

- 데이터 경로 검증 및 오류 처리
  ```java
  try {
      MyClass dataObject = configurator.load();
  } catch (ConfiguratorException e) {
      System.out.println("데이터를 로드하는 동안 오류가 발생했습니다: " + e.getMessage());
  }
  ```
  - 데이터를 로드하거나 저장할 때 유효하지 않거나, 파일이 손상된 경우 발생할 수 있는 오류를 처리한다. 'ConfiguratorException'를 사용하여 오류를 처리하고, 적절한 로그를 남길 수 있다.


- 데이터 일관성 유지
  - 데이터를 저장한 후, 반드시 'save()' 메소드를 호출하여 변경 사항을 파일에 반영해야 한다.
  - JSON 파일이 손상되지 않도록 저장 전 파일 경로와 파일 상태를 확인하고, 백업을 생성해두는 것이 좋다.

## 사용 시 주의사항
- 파일을 저장하거나 로드하기 전에 지정한 경로가 유효한지 확인해야 한다. 경로가 잘못되었거나 파일을 찾을 수 없는 경우, 
  'ConfiguratorException'이 발생할 수 있다.
- 특정 데이터 타입에 대한 직렬화 및 역직렬화를 하기 위해선 'TypeAdapter'를 올바르게 정의해야 한다. 어댑터가 올바르게 정의되지 않으면 
  JSON 변환 과정에서 오류가 발생할 수 있다.

## 예제 코드
```java
// Configurator 생성: 파일 경로와 데이터 타입을 지정
JsonConfigurator<MyClass> configurator = new JsonConfigurator<>("path/to/file.json", MyClass.class);

// 데이터 로드: 파일에서 데이터를 불러와 객체로 변환
try {
    MyClass dataObject = configurator.load();
    System.out.println("데이터가 로드되었습니다: " + dataObject.toString());
} catch (ConfiguratorException e) {
    System.out.println("데이터를 로드하는 동안 오류가 발생했습니다: " + e.getMessage());
}

// 데이터 수정: 불러온 데이터를 수정
dataObject.setSomeField("new value");

// 데이터 저장: 수정된 데이터를 파일에 저장
try {
    configurator.save(dataObject);
    System.out.println("데이터가 저장되었습니다.");
} catch (ConfiguratorException e) {
    System.out.println("데이터를 저장하는 동안 오류가 발생했습니다: " + e.getMessage());
}

// Gson 인스턴스 사용: 데이터를 JSON 문자열로 직렬화
Gson gson = Isekai.getIsekaiGson().getGson();
String jsonData = gson.toJson(dataObject);
System.out.println("JSON 데이터: " + jsonData);

// JSON 문자열을 객체로 역직렬화
MyClass newDataObject = gson.fromJson(jsonData, MyClass.class);
System.out.println("역직렬화된 데이터: " + newDataObject.toString());

// TypeAdapter 추가: 특정 클래스의 데이터를 변환할 때 사용할 어댑터를 추가
Isekai.getIsekaiGson().addTypeAdapter(MyClass.class, new MyClassAdapter());
System.out.println("MyClass에 대한 TypeAdapter가 추가되었습니다.");
```