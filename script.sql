USE [master]
GO
/****** Object:  Database [LAB231_TRAVELING]    Script Date: 6/22/2020 2:13:29 PM ******/
CREATE DATABASE [LAB231_TRAVELING]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LAB231_TRAVELING', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LAB231_TRAVELING.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LAB231_TRAVELING_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\LAB231_TRAVELING_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [LAB231_TRAVELING] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LAB231_TRAVELING].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LAB231_TRAVELING] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET ARITHABORT OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LAB231_TRAVELING] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LAB231_TRAVELING] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LAB231_TRAVELING] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LAB231_TRAVELING] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LAB231_TRAVELING] SET  MULTI_USER 
GO
ALTER DATABASE [LAB231_TRAVELING] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LAB231_TRAVELING] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LAB231_TRAVELING] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LAB231_TRAVELING] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LAB231_TRAVELING] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LAB231_TRAVELING] SET QUERY_STORE = OFF
GO
USE [LAB231_TRAVELING]
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount](
	[discountID] [nvarchar](50) NOT NULL,
	[expiryDate] [date] NULL,
	[discountPercent] [int] NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Discount_Registration]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Discount_Registration](
	[drID] [int] IDENTITY(1,1) NOT NULL,
	[discountID] [nvarchar](50) NULL,
	[userID] [nvarchar](50) NULL,
	[statusID] [int] NULL,
 CONSTRAINT [PK_Discount_Registration] PRIMARY KEY CLUSTERED 
(
	[drID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FromPlace]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FromPlace](
	[fromPlaceID] [int] IDENTITY(1,1) NOT NULL,
	[fromPlaceName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_StartingGate] PRIMARY KEY CLUSTERED 
(
	[fromPlaceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[total] [float] NULL,
	[discountID] [nvarchar](50) NULL,
	[userID] [nvarchar](50) NULL,
	[date] [date] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PriceLevel]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PriceLevel](
	[priceLevel] [int] NOT NULL,
	[priceLevelName] [nvarchar](50) NULL,
	[priceFrom] [float] NULL,
	[priceTo] [float] NULL,
 CONSTRAINT [PK_PriceLevel] PRIMARY KEY CLUSTERED 
(
	[priceLevel] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[userID] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[roleID] [int] NOT NULL,
	[statusID] [int] NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roleID] [int] NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[statusID] [int] NOT NULL,
	[statusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[statusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ToPlace]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ToPlace](
	[toPlaceID] [int] NOT NULL,
	[toPlaceName] [nvarchar](50) NULL,
 CONSTRAINT [PK_ToPlace] PRIMARY KEY CLUSTERED 
(
	[toPlaceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tour]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tour](
	[tourID] [nvarchar](50) NOT NULL,
	[tourName] [nvarchar](250) NULL,
	[price] [float] NULL,
	[fromDate] [date] NULL,
	[fromPlaceID] [int] NULL,
	[image] [nvarchar](250) NULL,
	[statusID] [int] NULL,
	[toPlaceID] [int] NULL,
	[toDate] [date] NULL,
	[quota] [int] NULL,
	[dateImport] [date] NULL,
 CONSTRAINT [PK_Tour] PRIMARY KEY CLUSTERED 
(
	[tourID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tour_Order]    Script Date: 6/22/2020 2:13:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tour_Order](
	[tourOrderID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int] NULL,
	[tourID] [nvarchar](50) NULL,
	[price] [float] NULL,
	[amount] [int] NULL,
	[total] [float] NULL,
 CONSTRAINT [PK_Tour_Order] PRIMARY KEY CLUSTERED 
(
	[tourOrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Discount] ([discountID], [expiryDate], [discountPercent]) VALUES (N'ABCD1111', CAST(N'2020-06-30' AS Date), 5)
INSERT [dbo].[Discount] ([discountID], [expiryDate], [discountPercent]) VALUES (N'ABCD1112', CAST(N'2020-06-30' AS Date), 10)
INSERT [dbo].[Discount] ([discountID], [expiryDate], [discountPercent]) VALUES (N'ABCD1113', CAST(N'2020-06-30' AS Date), 15)
INSERT [dbo].[Discount] ([discountID], [expiryDate], [discountPercent]) VALUES (N'ABCD1114', CAST(N'2020-06-30' AS Date), 20)
SET IDENTITY_INSERT [dbo].[Discount_Registration] ON 

INSERT [dbo].[Discount_Registration] ([drID], [discountID], [userID], [statusID]) VALUES (9, N'ABCD1111', N'hau1404', 2)
SET IDENTITY_INSERT [dbo].[Discount_Registration] OFF
SET IDENTITY_INSERT [dbo].[FromPlace] ON 

INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (1, N'Hồ Chí Minh')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (2, N'Hà Nội')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (3, N'Hải Phòng')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (4, N'Đà Nẵng')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (5, N'Nha Trang')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (6, N'Vũng Tàu')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (7, N'Bến Tre')
INSERT [dbo].[FromPlace] ([fromPlaceID], [fromPlaceName]) VALUES (8, N'Đà Lạt')
SET IDENTITY_INSERT [dbo].[FromPlace] OFF
SET IDENTITY_INSERT [dbo].[Order] ON 

INSERT [dbo].[Order] ([orderID], [total], [discountID], [userID], [date]) VALUES (20, 6080, N'ABCD1111', N'hau1404', CAST(N'2020-06-22' AS Date))
SET IDENTITY_INSERT [dbo].[Order] OFF
INSERT [dbo].[PriceLevel] ([priceLevel], [priceLevelName], [priceFrom], [priceTo]) VALUES (1, N'Tất cả', 0, -1)
INSERT [dbo].[PriceLevel] ([priceLevel], [priceLevelName], [priceFrom], [priceTo]) VALUES (2, N'Dưới 1 triệu', 0, 1000)
INSERT [dbo].[PriceLevel] ([priceLevel], [priceLevelName], [priceFrom], [priceTo]) VALUES (3, N'1-2 triệu', 1000, 2000)
INSERT [dbo].[PriceLevel] ([priceLevel], [priceLevelName], [priceFrom], [priceTo]) VALUES (4, N'2-4 triệu', 2000, 4000)
INSERT [dbo].[PriceLevel] ([priceLevel], [priceLevelName], [priceFrom], [priceTo]) VALUES (5, N'Trên 5 triệu', 5000, -1)
INSERT [dbo].[Registration] ([userID], [password], [roleID], [statusID], [name]) VALUES (N'customer', N'customer', 2, 1, N'customer')
INSERT [dbo].[Registration] ([userID], [password], [roleID], [statusID], [name]) VALUES (N'hau1404', N'hau1404', 2, 1, N'hau nguyen')
INSERT [dbo].[Registration] ([userID], [password], [roleID], [statusID], [name]) VALUES (N'hauadmin', N'hau123', 1, 1, N'admin')
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (1, N'admin')
INSERT [dbo].[Role] ([roleID], [roleName]) VALUES (2, N'customer')
INSERT [dbo].[Status] ([statusID], [statusName]) VALUES (1, N'active')
INSERT [dbo].[Status] ([statusID], [statusName]) VALUES (2, N'deactive')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (1, N'Sơn La')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (2, N'Sóc Trăng')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (3, N'Quy Nhơn')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (4, N'Bảo Lộc')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (5, N'Bến Tre')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (6, N'Đà Lạt')
INSERT [dbo].[ToPlace] ([toPlaceID], [toPlaceName]) VALUES (7, N'Bình Định')
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T01', N'DU LỊCH HỒ CHÍ MINH – CÁI BÈ – CẦN THƠ – CHÂU ĐỐC – BẠC LIÊU – CÀ MAU', 1200, CAST(N'2020-06-12' AS Date), 1, N'images/1.jpg', 1, 1, CAST(N'2020-06-21' AS Date), 5, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T02', N'DU LỊCH NHA TRANG THAM QUAN 4 ĐẢO, CHÙA LONG SƠN, NHÀ YẾN, THÁP BÀ PONAGAR, TẮM', 1500, CAST(N'2020-06-13' AS Date), 2, N'images/2.jpg', 1, 2, CAST(N'2020-06-21' AS Date), 5, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T03', N'DU LỊCH ĐÀ LẠT – NẤC THANG THIÊN ĐƯỜNG – HỒ VÔ CỰC BẰNG Ô TÔ TỪ TP. HỒ CHÍ MINH ( TOUR', 2100, CAST(N'2020-06-12' AS Date), 5, N'images/3.jpg', 1, 6, CAST(N'2020-06-21' AS Date), 5, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T04', N'DU LỊCH NHA TRANG - HÈ 2020 TỪ HÀ NỘI/TP. HỒ CHÍ MINH ( CHƯA BAO GỒM VÉ MÁY BAY) 4N/3Đ', 2200, CAST(N'2020-06-12' AS Date), 4, N'images/4.jpg', 1, 2, CAST(N'2020-06-29' AS Date), 5, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T05', N'DU LICH MIỀN NAM', 2300, CAST(N'2020-06-12' AS Date), 3, N'images/5.jpg', 1, 7, CAST(N'2020-06-21' AS Date), 5, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T06', N'TOUR NHA TRANG - ĐÀ LẠT BẰNG Ô TÔ HÈ 2020 5N/4Đ', 2500, CAST(N'2020-06-14' AS Date), 6, N'images/6.jpg', 1, 4, CAST(N'2020-06-29' AS Date), 5, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T07', N'DU LỊCH PHÚ QUỐC CÂU CÁ LẶN BIỂN NGẮM SAN HÔ HÈ NĂM 2020 TỪ TP. HỒ CHÍ MINH (TOUR', 5200, CAST(N'2020-06-25' AS Date), 3, N'images/10.jpg', 1, 2, CAST(N'2020-06-30' AS Date), 5, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T08', N'ĐÀ NẴNG - BÁN ĐẢO SƠN TRÀ - BÀ NÀ ( CẦU VÀNG) - HỘI AN - HUẾ - PHONG NHA 5N/4D (TOUR', 1200, CAST(N'2020-06-25' AS Date), 4, N'images/11.jpg', 1, 5, CAST(N'2020-06-30' AS Date), 6, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T09', N'DU LỊCH HÀ NỘI - HẠ LONG-SAPA-YÊN TỬ-TRÀNG AN-CHÙA HƯƠNG (7N/6Đ) CÓ VÉ MÁY BAY', 5100, CAST(N'2020-06-25' AS Date), 1, N'images/12.jpg', 1, 1, CAST(N'2020-06-30' AS Date), 9, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T10', N'DU LỊCH PHAN THIẾT – PHAN RANG – NHA TRANG KHỞI HÀNH BẰNG Ô TÔ', 2700, CAST(N'2020-06-11' AS Date), 7, N'images/7.jpg', 1, 3, CAST(N'2020-06-21' AS Date), 10, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T11', N'HÀ NỘI – QUY NHƠN – KỲ CO – EO GIÓ – PHÚ YÊN – GHỀNH RÁNG TIÊN SA (TOUR TRỌN GÓI) 4N/3Đ', 2900, CAST(N'2020-06-10' AS Date), 4, N'images/8.jpg', 1, 4, CAST(N'2020-06-29' AS Date), 7, CAST(N'2020-06-19' AS Date))
INSERT [dbo].[Tour] ([tourID], [tourName], [price], [fromDate], [fromPlaceID], [image], [statusID], [toPlaceID], [toDate], [quota], [dateImport]) VALUES (N'T12', N'DU LỊCH MÙ CANG CHẢI - BẢN LƯỚT - SAPA MÙA LÚA CHÍN CHINH PHỤC ĐỈNH FANSIFAN 3N/2Đ', 3100, CAST(N'2020-06-25' AS Date), 3, N'images/9.jpg', 1, 6, CAST(N'2020-06-30' AS Date), 8, CAST(N'2020-06-21' AS Date))
SET IDENTITY_INSERT [dbo].[Tour_Order] ON 

INSERT [dbo].[Tour_Order] ([tourOrderID], [orderID], [tourID], [price], [amount], [total]) VALUES (19, 20, N'T10', 2700, 1, 2700)
INSERT [dbo].[Tour_Order] ([tourOrderID], [orderID], [tourID], [price], [amount], [total]) VALUES (20, 20, N'T04', 2200, 1, 2200)
INSERT [dbo].[Tour_Order] ([tourOrderID], [orderID], [tourID], [price], [amount], [total]) VALUES (21, 20, N'T02', 1500, 1, 1500)
SET IDENTITY_INSERT [dbo].[Tour_Order] OFF
ALTER TABLE [dbo].[Discount_Registration]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Registration_Discount1] FOREIGN KEY([discountID])
REFERENCES [dbo].[Discount] ([discountID])
GO
ALTER TABLE [dbo].[Discount_Registration] CHECK CONSTRAINT [FK_Discount_Registration_Discount1]
GO
ALTER TABLE [dbo].[Discount_Registration]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Registration_Registration] FOREIGN KEY([userID])
REFERENCES [dbo].[Registration] ([userID])
GO
ALTER TABLE [dbo].[Discount_Registration] CHECK CONSTRAINT [FK_Discount_Registration_Registration]
GO
ALTER TABLE [dbo].[Discount_Registration]  WITH CHECK ADD  CONSTRAINT [FK_Discount_Registration_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Discount_Registration] CHECK CONSTRAINT [FK_Discount_Registration_Status]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Discount] FOREIGN KEY([discountID])
REFERENCES [dbo].[Discount] ([discountID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Discount]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_Registration] FOREIGN KEY([userID])
REFERENCES [dbo].[Registration] ([userID])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_Registration]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Role] FOREIGN KEY([roleID])
REFERENCES [dbo].[Role] ([roleID])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Role]
GO
ALTER TABLE [dbo].[Registration]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Registration] CHECK CONSTRAINT [FK_Registration_Status]
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD  CONSTRAINT [FK_Tour_FromPlace] FOREIGN KEY([fromPlaceID])
REFERENCES [dbo].[FromPlace] ([fromPlaceID])
GO
ALTER TABLE [dbo].[Tour] CHECK CONSTRAINT [FK_Tour_FromPlace]
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD  CONSTRAINT [FK_Tour_Status] FOREIGN KEY([statusID])
REFERENCES [dbo].[Status] ([statusID])
GO
ALTER TABLE [dbo].[Tour] CHECK CONSTRAINT [FK_Tour_Status]
GO
ALTER TABLE [dbo].[Tour]  WITH CHECK ADD  CONSTRAINT [FK_Tour_ToPlace] FOREIGN KEY([toPlaceID])
REFERENCES [dbo].[ToPlace] ([toPlaceID])
GO
ALTER TABLE [dbo].[Tour] CHECK CONSTRAINT [FK_Tour_ToPlace]
GO
ALTER TABLE [dbo].[Tour_Order]  WITH CHECK ADD  CONSTRAINT [FK_Tour_Order_Order] FOREIGN KEY([orderID])
REFERENCES [dbo].[Order] ([orderID])
GO
ALTER TABLE [dbo].[Tour_Order] CHECK CONSTRAINT [FK_Tour_Order_Order]
GO
ALTER TABLE [dbo].[Tour_Order]  WITH CHECK ADD  CONSTRAINT [FK_Tour_Order_Tour] FOREIGN KEY([tourID])
REFERENCES [dbo].[Tour] ([tourID])
GO
ALTER TABLE [dbo].[Tour_Order] CHECK CONSTRAINT [FK_Tour_Order_Tour]
GO
USE [master]
GO
ALTER DATABASE [LAB231_TRAVELING] SET  READ_WRITE 
GO
