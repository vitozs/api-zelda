package com.github.zeldaservice.model.favoriteModel;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuario_e_jogo_favorito")
@Data // evite usar @Data. Preferencialmente, use @Getter e @Setter, e, se precisar @AllArgsConstructor e @NoArgsConstructos
// a razão pra isso é que essa classe vai gerar métodos equalsAndHashCode, que é terrível pra testar em unit tests.
// isso funciona, mas dificulta a vida do dev batedor de laje do dia a dia
public class FavoriteGameModel {
// espaços!
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long id_usuario; // snake case em nome de field nãããããããão!!!! usa camel case
    @Column(name = "id_usuario")// se precisar que isso seja o nome mapeado pra coluna, usa o @Column("column_name")
    private Long idUsuario;
    private Long id_jogo;
}
