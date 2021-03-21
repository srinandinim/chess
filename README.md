# chess
*Completed in collaboration between Swapnil Napuri & Srinandini Marpaka*

### Description
2-player text-based chess game on the command-line </br>
Includes basic piece movement, castling, en passant, promotion, check, and checkmate </br>

#### Piece Movement
A piece can be moved through an input in the form 'FileRank FileRank', where the first argument represents the location of the piece selected and the second argument represents the location the piece should be moved to. For example, moving a white pawn up two spaces could be 'a2 a4'. </br>

#### Castling
To castle, move the king two spaces to the right or the left. As long as the requirements for castling are met, the corresponding rook will move to the correct location. For example, 'e1 c1' will move the white king to 'c1' and the left white rook to 'd1'. </br>

#### Promotion
A pawn promotion is indicated by putting the piece the pawn should be promoted to at the end of the move. For example, promoting a white pawn to a knight could be 'a7 a8 N'. If no piece is indicated, the pawn will be automatically promoted to a queen. </br>