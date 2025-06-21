# 🛒 Amazon Price Tracker

A real-time Amazon product price tracker built using **Java, Selenium WebDriver, and TestNG**. This automation script allows users to track prices of specific Amazon products and sends email notifications when prices drop.

---

## 🚀 Features

- 🔍 Tracks multiple Amazon product prices using dynamic XPath
- 🧪 Automated with TestNG and structured using the Page Object Model (POM)
- 📄 Product list managed via a JSON file for easy scalability


---

## 📦 Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven

## 🧪 How to Run

1. **Clone the repository:**

```bash
git clone https://github.com/YourUsername/AmazonPriceTracker.git
cd AmazonPriceTracker

2.**Add product details to ItemData.json**:
[

  {
    "item": "iPhone",
    "model": "16pro max",
    "target_price": 145000,
    "searchModel": "iPhone 16 Pro Max"
  },

    {
      "item": "Samsung TV",
      "model": "55+ inch",
      "target_price": 45000,
      "searchModel": "Samsung 138 cm (55 inches)"

    },
    {
      "item": "HP Laptop",
      "model": "15",
      "target_price": 80000,
      "searchModel": "HP 15"

    },
    {
      "item": "apple Smartwatch",
      "model": "series 10",
      "target_price": 40000,
      "searchModel": "Apple Watch Series 10"

    },
    {
      "item": "sony Headphones",
      "model": "noise cancellation",
      "target_price": 1000,
      "searchModel": "Active Noise Cancellation"

    }
]
3. **Run tests via TestNG:**
Right-click on testng.xml → Run as → TestNG Suite


