package com.ctoutweb.aet.domain.gameConfiguration.generateCalculMentalGame;

import com.ctoutweb.aet.domain.entity.gameText.gameEnd.IGameEndText;
import com.ctoutweb.aet.domain.provider.CoreFactory;
import com.ctoutweb.aet.domain.provider.generateMemoryCardGame.IDomainModelInstanceProvider;

public class CalculMentalText {
  private static final IDomainModelInstanceProvider PROVIDER = CoreFactory.MEMORY_CARD_DOMAIN_MODEL_INSTANCE_PROVIDER;
  public static final String GAME_TEXT_PRESENTATION = "Pépare toi a faire une suite de calcul. Attention tu davras les faire de tête";
  public static final String GAME_TITLE = "Les claculs de tête";
  public static final String GAME_VICTORY_GAME = "Bravooooo. Tu es trop fort. N'hésite pas à recommencer";

  // Chargement text de fin
  public static IGameEndText END_TEXT_EXCELLENT =  PROVIDER.provideEndText("Houaaaaa Excellent", "Tu as fais une excellente partie. Continue comme ça");
  public static IGameEndText END_TEXT_VERY_GOOD =  PROVIDER.provideEndText("Bravooooo", "Tu as fais une trés bonne partie. ne t'arrète pas");
  public static IGameEndText END_TEXT_GOOD =  PROVIDER.provideEndText("C'est bien", "Tu as fais une belle partie. Continue comme ça");
  public static IGameEndText END_TEXT_MEDIUM =  PROVIDER.provideEndText("Pas mal", "Tu t'en sors bien. Ne baisse pas les bras");
  public static IGameEndText END_TEXT_BAD =  PROVIDER.provideEndText("Ouchhhh", "C'est dur on dirait. Continue à te concentrer");
  public static IGameEndText END_TEXT_VERY_BAD =  PROVIDER.provideEndText("Ouilleeeeee", "Tu as souffert j'ai l'impression. Concentre toi, tu y arriveras");
  public static IGameEndText END_TEXT_LOOSE =  PROVIDER.provideEndText("Dommageeee", "Alors c'est pas dur de perdre?");

  public static final String GAME_LOOSE_TEXT = "C'est pas grave. Tu peux encore recommencer et t'améliorer";
}
