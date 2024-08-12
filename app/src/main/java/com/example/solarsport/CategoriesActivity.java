package com.example.solarsport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CategoriesActivity extends AppCompatActivity {

    ImageButton chomeb, ccategoriesb, cstatisticsb, cbenefitsb, cprofileb;
    LinearLayout homeContainer, categoriesContainer, statisticsContainer, benefitsContainer, profileContainer;
    private int userId;
    private String fullName;

    private CardView selectedCard;
    private CardView selectedHorizontalCard;

    private String selectedCategoryText;
    private String selectedSportSupplyText;

    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        // Obtener el ID y el nombre completo del usuario desde el Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        fullName = getIntent().getStringExtra("FULL_NAME");

        // Inicializar botones de navegación
        chomeb = findViewById(R.id.chomeb);
        ccategoriesb = findViewById(R.id.ccategoriesb);
        cstatisticsb = findViewById(R.id.cstatisticsb);
        cbenefitsb = findViewById(R.id.cbenefitsb);
        cprofileb = findViewById(R.id.cprofileb);

        // Configurar listeners para los botones de navegación
        chomeb.setOnClickListener(view -> navigateTo(MainActivity.class));
        ccategoriesb.setOnClickListener(view -> navigateTo(CategoriesActivity.class));
        cstatisticsb.setOnClickListener(view -> navigateTo(StatisticsActivity.class));
        cbenefitsb.setOnClickListener(view -> navigateTo(BenefitsActivity.class));
        cprofileb.setOnClickListener(view -> navigateTo(ProfileActivity.class));

        // Inicializar CardViews
        initializeCardViews();

        // Inicializar botón Next
        nextButton = findViewById(R.id.cbutton);
        nextButton.setEnabled(false);
        nextButton.setOnClickListener(view -> {
            if (selectedCategoryText != null && selectedSportSupplyText != null) {
                Intent intent = new Intent(CategoriesActivity.this, RegisterActivity.class);
                intent.putExtra("SELECTED_CATEGORY", selectedCategoryText);
                intent.putExtra("SELECTED_SPORT_SUPPLY", selectedSportSupplyText);
                startActivity(intent);
            }
        });
    }

    private void initializeCardViews() {
        CardView cardGridTied = findViewById(R.id.card_grid_tied);
        CardView cardOffGrid = findViewById(R.id.card_off_grid);
        CardView cardHybrid = findViewById(R.id.card_hybrid);

        cardGridTied.setOnClickListener(view -> selectCard(cardGridTied, "Grid Tied"));
        cardOffGrid.setOnClickListener(view -> selectCard(cardOffGrid, "Off Grid"));
        cardHybrid.setOnClickListener(view -> selectCard(cardHybrid, "Hybrid"));

        CardView cardTennis = findViewById(R.id.card_tennis);
        CardView cardSoccer = findViewById(R.id.card_soccer);
        CardView cardStadium = findViewById(R.id.card_stadium);
        CardView cardGym = findViewById(R.id.card_gym);
        CardView cardBasketball = findViewById(R.id.card_basketball);
        CardView cardVolleyball = findViewById(R.id.card_volleyball);

        cardTennis.setOnClickListener(view -> selectHorizontalCard(cardTennis, "Tennis"));
        cardSoccer.setOnClickListener(view -> selectHorizontalCard(cardSoccer, "Soccer"));
        cardStadium.setOnClickListener(view -> selectHorizontalCard(cardStadium, "Stadium"));
        cardGym.setOnClickListener(view -> selectHorizontalCard(cardGym, "Gym"));
        cardBasketball.setOnClickListener(view -> selectHorizontalCard(cardBasketball, "Basketball"));
        cardVolleyball.setOnClickListener(view -> selectHorizontalCard(cardVolleyball, "Volleyball"));
    }

    private void selectCard(CardView card, String text) {
        if (selectedCard != null) {
            selectedCard.setCardBackgroundColor(getResources().getColor(R.color.primaryLight));
        }
        card.setCardBackgroundColor(getResources().getColor(R.color.selectedCard));
        selectedCard = card;
        selectedCategoryText = text;
        checkSelections();
    }

    private void selectHorizontalCard(CardView card, String text) {
        if (selectedHorizontalCard != null) {
            selectedHorizontalCard.setCardBackgroundColor(getResources().getColor(R.color.primaryLight));
        }
        card.setCardBackgroundColor(getResources().getColor(R.color.selectedCard));
        selectedHorizontalCard = card;
        selectedSportSupplyText = text;
        checkSelections();
    }

    private void checkSelections() {
        nextButton.setEnabled(selectedCategoryText != null && selectedSportSupplyText != null);
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(CategoriesActivity.this, targetActivity);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("FULL_NAME", fullName);
        startActivity(intent);
    }
}