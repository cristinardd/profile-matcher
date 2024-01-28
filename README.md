![image](https://github.com/cristinardd/profile-matcher/assets/70025874/7b0b4acd-24f3-4786-8079-045027f7ede1)
## Database Schema Overview

### 1. `item`
- **Purpose**: Represents different items in the game.
- **Relationships**: Linked to the `inventory`, `campaign_has_item`, and `campaign_does_not_have_item` tables.
- **Rationale**: Items are central to player inventory and campaign requirements. They cater to different aspects of gameplay and campaign mechanics with distinct types (ITEM, COIN, CASH).

### 2. `country`
- **Purpose**: Stores country codes.
- **Relationships**: Linked to the `campaign_country` table and indirectly to the `player` table.
- **Rationale**: Used to define the geographical availability of campaigns and for potential player localization.

### 3. `campaign`
- **Purpose**: Contains details about various campaigns.
- **Relationships**: Linked to `campaign_level`, `campaign_country`, `campaign_has_item`, and `campaign_does_not_have_item`.
- **Rationale**: Campaigns, with their relationship to levels, countries, and items, define their eligibility criteria.

### 4. Eligibility Criteria Tables (`campaign_level`, `campaign_country`, `campaign_has_item`, `campaign_does_not_have_item`)
- **Purpose**: Define eligibility criteria for campaigns.
- **Relationships**: Link the `campaign` table with levels, countries, and items.
- **Rationale**: Enable campaigns to target players based on their level, location, and inventory, facilitating personalized experiences.

### 5. `clan`
- **Purpose**: Represents player groups or communities.
- **Relationships**: Linked to the `player` table through `player_clan`.
- **Rationale**: Clans are a social feature that allows players to associate with groups. The many-to-many relationship reflects the flexibility of player and clan associations.

### 6. `player`
- **Purpose**: Stores player information.
- **Relationships**: Linked to `device`, `inventory`, `player_clan`, and indirectly to `campaign` through business logic.
- **Rationale**: Central to the application, storing all key player data. Player attributes like level, country, and inventory items are crucial for campaign matching.

### 7. `device`
- **Purpose**: Details the devices used by players.
- **Relationships**: Linked to the `player` table through `player_device`.
- **Rationale**: Reflects the many-to-many relationship where players can use multiple devices, and devices can be shared by multiple players.

### 8. `inventory`
- **Purpose**: Keeps track of the items owned by players.
- **Relationships**: Linked to `player` and `item`.
- **Rationale**: Represents the items a player possesses, crucial for gameplay and campaign eligibility.

### 9. Junction Tables (`player_clan` and `player_device`)
- **Purpose**: Resolve many-to-many relationships between players and clans, and players and devices.
- **Rationale**: A common practice in relational database design to efficiently manage many-to-many relationships.

## Services

### CampaignService
- **Responsibilities**: Handles campaign-related operations.
- **Methods**:
  - `getAllCampaigns()`: Retrieves all campaigns and maps them to `CampaignDto`.
  - `findMatchingCampaigns(Player player)`: Matches player profiles with campaigns based on criteria like level, country, and item requirements.

### PlayerService
- **Responsibilities**: Manages player-related functionalities.
- **Methods**:
  - `findAll()`: Retrieves all players and maps them to `PlayerDto`.
  - `matchPlayerProfile(String playerId)`: Matches a player's profile with eligible campaigns and updates the player's active campaign list.

