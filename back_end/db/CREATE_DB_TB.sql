-- Create a new database called 'SIKI_SHOP_APP'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'SIKI_SHOP_APP'
)
CREATE DATABASE SIKI_SHOP_APP
GO
-- Create a new table called '[USERS]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[USERS]') IS NOT NULL
DROP TABLE [dbo].[USERS]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[USERS]
(
    [id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [fullname] NVARCHAR(100) DEFAULT '',
    [date_of_bird] DATE,
    [phone_number] VARCHAR(10) NOT NULL,
    [address] NVARCHAR(200) DEFAULT '',
    [password] VARCHAR(100) NOT NULL DEFAULT '', -- Mat Khau Ma Hoa
    [create_at] DATETIME,  
    [update_at] DATETIME,
    [is_active] TINYINT DEFAULT 1,
    [facebook_account] INT DEFAULT 0,  
    [google_account] INT DEFAULT 0  
);
GO

-- Create a new table called '[TOKENS]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[TOKENS]') IS NOT NULL
DROP TABLE [dbo].[TOKENS]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[TOKENS]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [token] VARCHAR(255) UNIQUE NOT NULL,
    [token_type] VARCHAR(50) NOT NULL,
    [expiration_date] DATETIME,
    [revoked] TINYINT NOT NULL,
    [expired] TINYINT NOT NULL,
    [user_id] INT,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);
GO

-- Create a new table called '[SOCIAL_ACCOUNTS]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[SOCIAL_ACCOUNTS]') IS NOT NULL
DROP TABLE [dbo].[SOCIAL_ACCOUNTS]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[SOCIAL_ACCOUNTS]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [provider] VARCHAR(20) NOT NULL , -- Ten Nha SOCIAL NETWORD
    [provider_id] VARCHAR(50) NOT NULL,
    [email] VARCHAR(150) NOT NULL, --Email TK
    [name] NVARCHAR(100) NOT NULL, -- Ten nguoi Dung
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);
GO

-- Create a new table called '[CATEGORIES]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[CATEGORIES]') IS NOT NULL
DROP TABLE [dbo].[CATEGORIES]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[CATEGORIES]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [name] NVARCHAR(50) NOT NULL DEFAULT '',
);
GO

-- Create a new table called '[PRODUCTS]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[PRODUCTS]') IS NOT NULL
DROP TABLE [dbo].[PRODUCTS]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[PRODUCTS]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [name] NVARCHAR(50) NOT NULL DEFAULT '',
    [price] FLOAT NOT NULL CHECK ([price] >= 0),
    [thumbnail] NVARCHAR(MAX) DEFAULT '',
    [description] TEXT DEFAULT '',
    [category_id] INT,
    [create_at] DATETIME,  
    [update_at] DATETIME,
    FOREIGN KEY (category_id) REFERENCES CATEGORIES(id)
);
GO

-- Create a new table called '[STATUS_CODES]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[STATUS_CODES]') IS NOT NULL
DROP TABLE [dbo].[STATUS_CODES]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[STATUS_CODES]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [STATUS_NAME] VARCHAR(20) NOT NULL
);

-- Create a new table called '[ORDERS]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[ORDERS]') IS NOT NULL
DROP TABLE [dbo].[ORDERS]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[ORDERS]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [user_id] INT,
    [fullname] NVARCHAR(100) DEFAULT '',
    [email] NVARCHAR(100) DEFAULT '',
    [phone_number] VARCHAR(100) NOT NULL DEFAULT '',
    [address] NVARCHAR(200) NOT NULL DEFAULT '',
    [note] NVARCHAR(100) DEFAULT '',
    [order_date] DATETIME DEFAULT CURRENT_TIMESTAMP,
    [status] VARCHAR(20),
    [total_money] FLOAT CHECK([total_money] >= 0),
    [shipping_method] VARCHAR(100),
    [shipping_address] VARCHAR(100),
    [shipping_date] DATE,
    [shipping_number] VARCHAR(100),
    [payment_method] VARCHAR(100),
    [status_codes_id] INT,
    [active] TINYINT,
    FOREIGN KEY (user_id) REFERENCES USERS(id),
    FOREIGN KEY (status_codes_id) REFERENCES STATUS_CODES(Id)
);

GO
-- Create a new table called '[ORDER_DETAILS]' in schema '[dbo]'
-- Drop the table if it already exists
IF OBJECT_ID('[dbo].[ORDER_DETAILS]') IS NOT NULL
DROP TABLE [dbo].[ORDER_DETAILS]
GO
-- Create the table in the specified schema
CREATE TABLE [dbo].[ORDER_DETAILS]
(
    [Id] INT NOT NULL IDENTITY(1, 1) PRIMARY KEY, -- Primary Key column
    [order_id] INT,
    [product_id] INT,
    [price] FLOAT CHECK ([price] >= 0),
    [number_of_products] INT CHECK ([number_of_products] > 0),
    [total_money] FLOAT CHECK ([total_money] >= 0),
    [color] NVARCHAR(20) DEFAULT '',
    FOREIGN KEY (order_id) REFERENCES ORDERS(id),
    FOREIGN KEY (product_id) REFERENCES PRODUCTS(id)
);
GO