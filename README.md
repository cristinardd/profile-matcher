![image](https://github.com/cristinardd/profile-matcher/assets/70025874/7b0b4acd-24f3-4786-8079-045027f7ede1)
The database design reflects the relationships between various entities in a gaming context. Let's break down these relationships and understand their rationale:

1. item -> 
Purpose: Represents different items in the game.
Relationships: Linked to the inventory, campaign_has_item, and campaign_does_not_have_item tables.
Rationale: Items are central to player inventory and campaign requirements. The distinct types (ITEM, COIN, CASH) cater to different aspects of gameplay and campaign mechanics.
3. country ->
Purpose: Stores country codes.
Relationships: Linked to the campaign_country table and indirectly to the player table.
Rationale: Used to define the geographical availability of campaigns and could potentially be used to localize player.
4. campaign ->
Purpose: Contains details about various campaigns.
Relationships: Linked to campaign_level, campaign_country, campaign_has_item, and campaign_does_not_have_item.
Rationale: Campaigns are a core feature, and their relationship with levels, countries, and items defines their eligibility criteria.
5. campaign_level, campaign_country, campaign_has_item, campaign_does_not_have_item->
Purpose: Define eligibility criteria for campaigns.
Relationships: Each links the campaign table with levels, countries, and items.
Rationale: These tables allow campaigns to target players based on their level, location, and inventory, enabling personalized and targeted gaming experiences.
6. clan ->
Purpose: Represents player groups or communities.
Relationships: Linked to the player table through player_clan.
Rationale: Clans are a social feature, allowing players to associate with groups. The many-to-many relationship reflects that players can belong to multiple clans and clans can have multiple players.
7. player ->
Purpose: Stores player information.
Relationships: Linked to device, inventory, player_clan, and indirectly to campaign through business logic.
Rationale: Central to the application, storing all key player data. The player's attributes like level, country, and inventory items are used to match with campaigns.
8. device ->
Purpose: Details the devices used by players.
Relationships: Linked to the player table through player_device.
Rationale: Reflects the many-to-many relationship where players can use multiple devices, and devices can be used by multiple players.
9. inventory ->
Purpose: Keeps track of the items owned by players.
Relationships: Linked to player and item.
Rationale: Represents what items a player possesses, important for gameplay and determining campaign eligibility.
10. player_clan and player_device ->
Purpose: Junction tables for many-to-many relationships.
Rationale: These tables resolve the many-to-many relationships between players and clans, and players and devices, a common practice in relational database design to handle such relationships efficiently.
