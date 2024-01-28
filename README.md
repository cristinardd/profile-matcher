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
 

  
## Controllers Overview

Controllers in this application serve as the entry points for HTTP requests. They handle incoming requests related to campaigns and players, invoking the corresponding services to process these requests.

### 1. CampaignController

- **Responsibilities**: Manages HTTP requests related to campaign data.
- **Endpoints**:
  - **GET `/getCampaigns`**: 
    - **Functionality**: Fetches a list of all current campaigns.
    - **Return Type**: `ResponseEntity<List<CampaignDto>>`
    - **Description**: Calls `campaignService.getAllCampaigns()` to retrieve all campaigns and returns them in a response entity.
   
       
<details>
  <summary>Click to expand JSON /getCampaigns response</summary>
  
  ```json
  [
      {
          "name": "campaign_2",
          "game": "game_2",
          "priority": 9.5,
          "startDate": "2024-01-20T00:00:00",
          "endDate": "2024-02-20T00:00:00",
          "enabled": true,
          "lastUpdated": "2024-01-28T11:51:11.539885",
          "matchers": {
              "level": {
                  "min": 0,
                  "max": 0
              },
              "has": {
                  "country": [],
                  "items": []
              },
              "doesNotHave": {
                  "items": []
              }
          }
      },
      {
          "name": "campaign_3",
          "game": "game_3",
          "priority": 8.5,
          "startDate": "2024-02-15T00:00:00",
          "endDate": "2024-03-15T00:00:00",
          "enabled": true,
          "lastUpdated": "2024-01-28T11:51:11.539885",
          "matchers": {
              "level": {
                  "min": 0,
                  "max": 0
              },
              "has": {
                  "country": [],
                  "items": []
              },
              "doesNotHave": {
                  "items": []
              }
          }
      },
      {
          "name": "all_players_campaign",
          "game": "universal_game",
          "priority": 8.0,
          "startDate": "2024-01-01T00:00:00",
          "endDate": "2024-04-01T00:00:00",
          "enabled": true,
          "lastUpdated": "2024-01-28T11:56:40.786564",
          "matchers": {
              "level": {
                  "min": 1,
                  "max": 10
              },
              "has": {
                  "country": ["US", "RO", "CA"],
                  "items": []
              },
              "doesNotHave": {
                  "items": []
              }
          }
      },
      {
          "name": "mycampaign",
          "game": "mygame",
          "priority": 10.5,
          "startDate": "2022-01-25T00:00:00",
          "endDate": "2025-02-25T00:00:00",
          "enabled": true,
          "lastUpdated": "2024-01-28T11:46:58",
          "matchers": {
              "level": {
                  "min": 1,
                  "max": 3
              },
              "has": {
                  "country": ["CA", "RO", "US"],
                  "items": ["item_1"]
              },
              "doesNotHave": {
                  "items": ["item_4"]
              }
          }
      }
  ]

```
  </details>

  
### 2. PlayerController

- **Responsibilities**: Handles HTTP requests related to player data.
- **Endpoints**:
  - **GET `/api/v1/findAll`**: 
    - **Functionality**: Retrieves a list of all players.
    - **Return Type**: `ResponseEntity<List<PlayerDto>>`
    - **Description**: Invokes `playerService.findAll()` to fetch all players and returns them in a response entity.
   
    
    <details>
    <summary>Click to expand JSON /findAll response</summary>

```json
[
    {
        "playerId": "p97983be2-98b7-11e7-90cf-82playerid3",
        "credential": "credential_3",
        "lastSession": "2021-03-25T15:00:00",
        "created": "2021-03-12T15:00:00",
        "lastPurchase": "2021-03-24T15:00:00",
        "modified": "2024-01-28T13:56:45.700113",
        "birthdate": "1998-07-20T00:00:00",
        "gender": "MALE",
        "customfield": "custom_data_3",
        "totalSpent": 500,
        "level": 4,
        "totalRefund": 0,
        "totalTransactions": 7,
        "totalPlaytime": 180,
        "language": "en",
        "xp": 1200,
        "country": "US",
        "activeCampaigns": null,
        "clan": [
            {
                "clanId": "123456",
                "name": "Hello world clan"
            },
            {
                "clanId": "1",
                "name": "new clan"
            }
        ],
        "inventory": {},
        "devices": [
            {
                "id": 2,
                "model": "model_2",
                "carrier": "carrier_2",
                "firmware": "firmware_2"
            },
            {
                "id": 3,
                "model": "model_3",
                "carrier": "carrier_3",
                "firmware": "firmware_3"
            }
        ]
    },
    {
        "playerId": "97983be2-98b7-11e7-90cf-082e5f28d836",
        "credential": "apple_credential",
        "lastSession": "2021-01-23T13:37:17",
        "created": "2021-01-10T13:37:17",
        "lastPurchase": "2021-01-22T13:37:17",
        "modified": "2024-01-28T13:58:05.85111",
        "birthdate": "2000-01-10T13:37:17",
        "gender": "MALE",
        "customfield": "mycustom",
        "totalSpent": 400,
        "level": 3,
        "totalRefund": 0,
        "totalTransactions": 5,
        "totalPlaytime": 144,
        "language": "fr",
        "xp": 1000,
        "country": "CA",
        "activeCampaigns": null,
        "clan": [
            {
                "clanId": "123456",
                "name": "Hello world clan"
            }
        ],
        "inventory": {
            "coins": 123,
            "item_1": 1,
            "cash": 123,
            "item_55": 2,
            "item_34": 3
        },
        "devices": [
            {
                "id": 1,
                "model": "apple iphone 11",
                "carrier": "vodafone",
                "firmware": "123"
            }
        ]
    }
]
```
</details>

  - **GET `/api/v1/get_client_config/{playerId}`**: 
    - **Functionality**: Fetches the client configuration for a specific player.
    - **Path Variable**: `playerId` (String)
    - **Return Type**: `ResponseEntity<PlayerDto>`
    - **Description**: Uses `playerService.matchPlayerProfile(playerId)` to retrieve the matched player profile based on active campaigns and returns the result.
   <details>
  <summary>Click to expand JSON /get_client_config/97983be2-98b7-11e7-90cf-082e5f28d836 response</summary>
  
  ```json
{
    "playerId": "97983be2-98b7-11e7-90cf-082e5f28d836",
    "credential": "apple_credential",
    "lastSession": "2021-01-23T13:37:17",
    "created": "2021-01-10T13:37:17",
    "lastPurchase": "2021-01-22T13:37:17",
    "modified": "2024-01-28T13:58:05.85111",
    "birthdate": "2000-01-10T11:37:17.000+00:00",
    "gender": "MALE",
    "customfield": "mycustom",
    "totalSpent": 400,
    "level": 3,
    "totalRefund": 0,
    "totalTransactions": 5,
    "totalPlaytime": 144,
    "language": "fr",
    "xp": 1000,
    "country": "CA",
    "activeCampaigns": [
        {
            "name": "all_players_campaign",
            "game": "universal_game",
            "priority": 8.0,
            "startDate": "2024-01-01T00:00:00",
            "endDate": "2024-04-01T00:00:00",
            "enabled": true,
            "lastUpdated": "2024-01-28T11:56:40.786564",
            "matchers": {
                "level": {
                    "min": 1,
                    "max": 10
                },
                "has": {
                    "country": ["RO", "CA", "US"],
                    "items": []
                },
                "doesNotHave": {
                    "items": []
                }
            }
        },
        {
            "name": "mycampaign",
            "game": "mygame",
            "priority": 10.5,
            "startDate": "2022-01-25T00:00:00",
            "endDate": "2025-02-25T00:00:00",
            "enabled": true,
            "lastUpdated": "2024-01-28T11:46:58",
            "matchers": {
                "level": {
                    "min": 1,
                    "max": 3
                },
                "has": {
                    "country": ["RO", "CA", "US"],
                    "items": ["item_1"]
                },
                "doesNotHave": {
                    "items": ["item_4"]
                }
            }
        }
    ],
    "clan": [
        {
            "clanId": "123456",
            "name": "Hello world clan"
        }
    ],
    "inventory": {
        "coins": 123,
        "item_1": 1,
        "cash": 123,
        "item_55": 2,
        "item_34": 3
    },
    "devices": [
        {
            "id": 1,
            "model": "apple iphone 11",
            "carrier": "vodafone",
            "firmware": "123"
        }
    ]
}
```
</details>
