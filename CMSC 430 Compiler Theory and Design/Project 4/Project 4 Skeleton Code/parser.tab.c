/* A Bison parser, made by GNU Bison 3.8.2.  */

/* Bison implementation for Yacc-like parsers in C

   Copyright (C) 1984, 1989-1990, 2000-2015, 2018-2021 Free Software Foundation,
   Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <https://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* DO NOT RELY ON FEATURES THAT ARE NOT DOCUMENTED in the manual,
   especially those whose name start with YY_ or yy_.  They are
   private implementation details that can be changed or removed.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output, and Bison version.  */
#define YYBISON 30802

/* Bison version string.  */
#define YYBISON_VERSION "3.8.2"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1




/* First part of user prologue.  */
#line 4 "parser.y"

#include <string>

using namespace std;

#include "types.h"
#include "listing.h"
#include "symbols.h"

int yylex();
void yyerror(const char* message);
Types find(Symbols<Types>& table, CharPtr identifier, string kind);
void insertScalar(CharPtr identifier, Types type);
void insertList(CharPtr identifier, Types type);

Symbols<Types> scalars;
Symbols<Types> lists;
Types functionReturnType;

#line 91 "parser.tab.c"

# ifndef YY_CAST
#  ifdef __cplusplus
#   define YY_CAST(Type, Val) static_cast<Type> (Val)
#   define YY_REINTERPRET_CAST(Type, Val) reinterpret_cast<Type> (Val)
#  else
#   define YY_CAST(Type, Val) ((Type) (Val))
#   define YY_REINTERPRET_CAST(Type, Val) ((Type) (Val))
#  endif
# endif
# ifndef YY_NULLPTR
#  if defined __cplusplus
#   if 201103L <= __cplusplus
#    define YY_NULLPTR nullptr
#   else
#    define YY_NULLPTR 0
#   endif
#  else
#   define YY_NULLPTR ((void*)0)
#  endif
# endif

#include "parser.tab.h"
/* Symbol kind.  */
enum yysymbol_kind_t
{
  YYSYMBOL_YYEMPTY = -2,
  YYSYMBOL_YYEOF = 0,                      /* "end of file"  */
  YYSYMBOL_YYerror = 1,                    /* error  */
  YYSYMBOL_YYUNDEF = 2,                    /* "invalid token"  */
  YYSYMBOL_IDENTIFIER = 3,                 /* IDENTIFIER  */
  YYSYMBOL_INT_LITERAL = 4,                /* INT_LITERAL  */
  YYSYMBOL_REAL_LITERAL = 5,               /* REAL_LITERAL  */
  YYSYMBOL_CHAR_LITERAL = 6,               /* CHAR_LITERAL  */
  YYSYMBOL_ADDOP = 7,                      /* ADDOP  */
  YYSYMBOL_MULOP = 8,                      /* MULOP  */
  YYSYMBOL_MODOP = 9,                      /* MODOP  */
  YYSYMBOL_EXPOP = 10,                     /* EXPOP  */
  YYSYMBOL_NEGOP = 11,                     /* NEGOP  */
  YYSYMBOL_ANDOP = 12,                     /* ANDOP  */
  YYSYMBOL_OROP = 13,                      /* OROP  */
  YYSYMBOL_NOTOP = 14,                     /* NOTOP  */
  YYSYMBOL_RELOP = 15,                     /* RELOP  */
  YYSYMBOL_ARROW = 16,                     /* ARROW  */
  YYSYMBOL_BEGIN_ = 17,                    /* BEGIN_  */
  YYSYMBOL_CASE = 18,                      /* CASE  */
  YYSYMBOL_CHARACTER = 19,                 /* CHARACTER  */
  YYSYMBOL_ELSE = 20,                      /* ELSE  */
  YYSYMBOL_ELSIF = 21,                     /* ELSIF  */
  YYSYMBOL_END = 22,                       /* END  */
  YYSYMBOL_ENDIF = 23,                     /* ENDIF  */
  YYSYMBOL_ENDFOLD = 24,                   /* ENDFOLD  */
  YYSYMBOL_ENDSWITCH = 25,                 /* ENDSWITCH  */
  YYSYMBOL_FOLD = 26,                      /* FOLD  */
  YYSYMBOL_FUNCTION = 27,                  /* FUNCTION  */
  YYSYMBOL_IF = 28,                        /* IF  */
  YYSYMBOL_INTEGER = 29,                   /* INTEGER  */
  YYSYMBOL_IS = 30,                        /* IS  */
  YYSYMBOL_LEFT = 31,                      /* LEFT  */
  YYSYMBOL_LIST = 32,                      /* LIST  */
  YYSYMBOL_OF = 33,                        /* OF  */
  YYSYMBOL_OTHERS = 34,                    /* OTHERS  */
  YYSYMBOL_REAL = 35,                      /* REAL  */
  YYSYMBOL_RETURNS = 36,                   /* RETURNS  */
  YYSYMBOL_RIGHT = 37,                     /* RIGHT  */
  YYSYMBOL_SWITCH = 38,                    /* SWITCH  */
  YYSYMBOL_THEN = 39,                      /* THEN  */
  YYSYMBOL_WHEN = 40,                      /* WHEN  */
  YYSYMBOL_41_ = 41,                       /* ';'  */
  YYSYMBOL_42_ = 42,                       /* ','  */
  YYSYMBOL_43_ = 43,                       /* ':'  */
  YYSYMBOL_44_ = 44,                       /* '('  */
  YYSYMBOL_45_ = 45,                       /* ')'  */
  YYSYMBOL_YYACCEPT = 46,                  /* $accept  */
  YYSYMBOL_function = 47,                  /* function  */
  YYSYMBOL_function_header = 48,           /* function_header  */
  YYSYMBOL_optional_parameters = 49,       /* optional_parameters  */
  YYSYMBOL_parameters = 50,                /* parameters  */
  YYSYMBOL_parameter = 51,                 /* parameter  */
  YYSYMBOL_variables = 52,                 /* variables  */
  YYSYMBOL_variable = 53,                  /* variable  */
  YYSYMBOL_type = 54,                      /* type  */
  YYSYMBOL_list = 55,                      /* list  */
  YYSYMBOL_expressions = 56,               /* expressions  */
  YYSYMBOL_body = 57,                      /* body  */
  YYSYMBOL_statement_ = 58,                /* statement_  */
  YYSYMBOL_statement = 59,                 /* statement  */
  YYSYMBOL_elsif_clauses = 60,             /* elsif_clauses  */
  YYSYMBOL_cases = 61,                     /* cases  */
  YYSYMBOL_case = 62,                      /* case  */
  YYSYMBOL_direction = 63,                 /* direction  */
  YYSYMBOL_operator = 64,                  /* operator  */
  YYSYMBOL_list_choice = 65,               /* list_choice  */
  YYSYMBOL_condition = 66,                 /* condition  */
  YYSYMBOL_or_condition = 67,              /* or_condition  */
  YYSYMBOL_and_condition = 68,             /* and_condition  */
  YYSYMBOL_not_condition = 69,             /* not_condition  */
  YYSYMBOL_relation = 70,                  /* relation  */
  YYSYMBOL_expression = 71,                /* expression  */
  YYSYMBOL_term = 72,                      /* term  */
  YYSYMBOL_power = 73,                     /* power  */
  YYSYMBOL_unary = 74,                     /* unary  */
  YYSYMBOL_primary = 75                    /* primary  */
};
typedef enum yysymbol_kind_t yysymbol_kind_t;




#ifdef short
# undef short
#endif

/* On compilers that do not define __PTRDIFF_MAX__ etc., make sure
   <limits.h> and (if available) <stdint.h> are included
   so that the code can choose integer types of a good width.  */

#ifndef __PTRDIFF_MAX__
# include <limits.h> /* INFRINGES ON USER NAME SPACE */
# if defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stdint.h> /* INFRINGES ON USER NAME SPACE */
#  define YY_STDINT_H
# endif
#endif

/* Narrow types that promote to a signed type and that can represent a
   signed or unsigned integer of at least N bits.  In tables they can
   save space and decrease cache pressure.  Promoting to a signed type
   helps avoid bugs in integer arithmetic.  */

#ifdef __INT_LEAST8_MAX__
typedef __INT_LEAST8_TYPE__ yytype_int8;
#elif defined YY_STDINT_H
typedef int_least8_t yytype_int8;
#else
typedef signed char yytype_int8;
#endif

#ifdef __INT_LEAST16_MAX__
typedef __INT_LEAST16_TYPE__ yytype_int16;
#elif defined YY_STDINT_H
typedef int_least16_t yytype_int16;
#else
typedef short yytype_int16;
#endif

/* Work around bug in HP-UX 11.23, which defines these macros
   incorrectly for preprocessor constants.  This workaround can likely
   be removed in 2023, as HPE has promised support for HP-UX 11.23
   (aka HP-UX 11i v2) only through the end of 2022; see Table 2 of
   <https://h20195.www2.hpe.com/V2/getpdf.aspx/4AA4-7673ENW.pdf>.  */
#ifdef __hpux
# undef UINT_LEAST8_MAX
# undef UINT_LEAST16_MAX
# define UINT_LEAST8_MAX 255
# define UINT_LEAST16_MAX 65535
#endif

#if defined __UINT_LEAST8_MAX__ && __UINT_LEAST8_MAX__ <= __INT_MAX__
typedef __UINT_LEAST8_TYPE__ yytype_uint8;
#elif (!defined __UINT_LEAST8_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST8_MAX <= INT_MAX)
typedef uint_least8_t yytype_uint8;
#elif !defined __UINT_LEAST8_MAX__ && UCHAR_MAX <= INT_MAX
typedef unsigned char yytype_uint8;
#else
typedef short yytype_uint8;
#endif

#if defined __UINT_LEAST16_MAX__ && __UINT_LEAST16_MAX__ <= __INT_MAX__
typedef __UINT_LEAST16_TYPE__ yytype_uint16;
#elif (!defined __UINT_LEAST16_MAX__ && defined YY_STDINT_H \
       && UINT_LEAST16_MAX <= INT_MAX)
typedef uint_least16_t yytype_uint16;
#elif !defined __UINT_LEAST16_MAX__ && USHRT_MAX <= INT_MAX
typedef unsigned short yytype_uint16;
#else
typedef int yytype_uint16;
#endif

#ifndef YYPTRDIFF_T
# if defined __PTRDIFF_TYPE__ && defined __PTRDIFF_MAX__
#  define YYPTRDIFF_T __PTRDIFF_TYPE__
#  define YYPTRDIFF_MAXIMUM __PTRDIFF_MAX__
# elif defined PTRDIFF_MAX
#  ifndef ptrdiff_t
#   include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  endif
#  define YYPTRDIFF_T ptrdiff_t
#  define YYPTRDIFF_MAXIMUM PTRDIFF_MAX
# else
#  define YYPTRDIFF_T long
#  define YYPTRDIFF_MAXIMUM LONG_MAX
# endif
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif defined __STDC_VERSION__ && 199901 <= __STDC_VERSION__
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned
# endif
#endif

#define YYSIZE_MAXIMUM                                  \
  YY_CAST (YYPTRDIFF_T,                                 \
           (YYPTRDIFF_MAXIMUM < YY_CAST (YYSIZE_T, -1)  \
            ? YYPTRDIFF_MAXIMUM                         \
            : YY_CAST (YYSIZE_T, -1)))

#define YYSIZEOF(X) YY_CAST (YYPTRDIFF_T, sizeof (X))


/* Stored state numbers (used for stacks). */
typedef yytype_uint8 yy_state_t;

/* State numbers in computations.  */
typedef int yy_state_fast_t;

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(Msgid) dgettext ("bison-runtime", Msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(Msgid) Msgid
# endif
#endif


#ifndef YY_ATTRIBUTE_PURE
# if defined __GNUC__ && 2 < __GNUC__ + (96 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_PURE __attribute__ ((__pure__))
# else
#  define YY_ATTRIBUTE_PURE
# endif
#endif

#ifndef YY_ATTRIBUTE_UNUSED
# if defined __GNUC__ && 2 < __GNUC__ + (7 <= __GNUC_MINOR__)
#  define YY_ATTRIBUTE_UNUSED __attribute__ ((__unused__))
# else
#  define YY_ATTRIBUTE_UNUSED
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YY_USE(E) ((void) (E))
#else
# define YY_USE(E) /* empty */
#endif

/* Suppress an incorrect diagnostic about yylval being uninitialized.  */
#if defined __GNUC__ && ! defined __ICC && 406 <= __GNUC__ * 100 + __GNUC_MINOR__
# if __GNUC__ * 100 + __GNUC_MINOR__ < 407
#  define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN                           \
    _Pragma ("GCC diagnostic push")                                     \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")
# else
#  define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN                           \
    _Pragma ("GCC diagnostic push")                                     \
    _Pragma ("GCC diagnostic ignored \"-Wuninitialized\"")              \
    _Pragma ("GCC diagnostic ignored \"-Wmaybe-uninitialized\"")
# endif
# define YY_IGNORE_MAYBE_UNINITIALIZED_END      \
    _Pragma ("GCC diagnostic pop")
#else
# define YY_INITIAL_VALUE(Value) Value
#endif
#ifndef YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
# define YY_IGNORE_MAYBE_UNINITIALIZED_END
#endif
#ifndef YY_INITIAL_VALUE
# define YY_INITIAL_VALUE(Value) /* Nothing. */
#endif

#if defined __cplusplus && defined __GNUC__ && ! defined __ICC && 6 <= __GNUC__
# define YY_IGNORE_USELESS_CAST_BEGIN                          \
    _Pragma ("GCC diagnostic push")                            \
    _Pragma ("GCC diagnostic ignored \"-Wuseless-cast\"")
# define YY_IGNORE_USELESS_CAST_END            \
    _Pragma ("GCC diagnostic pop")
#endif
#ifndef YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_BEGIN
# define YY_IGNORE_USELESS_CAST_END
#endif


#define YY_ASSERT(E) ((void) (0 && (E)))

#if 1

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
      /* Use EXIT_SUCCESS as a witness for stdlib.h.  */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's 'empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (0)
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
             && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* 1 */

#if (! defined yyoverflow \
     && (! defined __cplusplus \
         || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yy_state_t yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (YYSIZEOF (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (YYSIZEOF (yy_state_t) + YYSIZEOF (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)                           \
    do                                                                  \
      {                                                                 \
        YYPTRDIFF_T yynewbytes;                                         \
        YYCOPY (&yyptr->Stack_alloc, Stack, yysize);                    \
        Stack = &yyptr->Stack_alloc;                                    \
        yynewbytes = yystacksize * YYSIZEOF (*Stack) + YYSTACK_GAP_MAXIMUM; \
        yyptr += yynewbytes / YYSIZEOF (*yyptr);                        \
      }                                                                 \
    while (0)

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from SRC to DST.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(Dst, Src, Count) \
      __builtin_memcpy (Dst, Src, YY_CAST (YYSIZE_T, (Count)) * sizeof (*(Src)))
#  else
#   define YYCOPY(Dst, Src, Count)              \
      do                                        \
        {                                       \
          YYPTRDIFF_T yyi;                      \
          for (yyi = 0; yyi < (Count); yyi++)   \
            (Dst)[yyi] = (Src)[yyi];            \
        }                                       \
      while (0)
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  5
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   149

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  46
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  30
/* YYNRULES -- Number of rules.  */
#define YYNRULES  65
/* YYNSTATES -- Number of states.  */
#define YYNSTATES  142

/* YYMAXUTOK -- Last valid token kind.  */
#define YYMAXUTOK   295


/* YYTRANSLATE(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex, with out-of-bounds checking.  */
#define YYTRANSLATE(YYX)                                \
  (0 <= (YYX) && (YYX) <= YYMAXUTOK                     \
   ? YY_CAST (yysymbol_kind_t, yytranslate[YYX])        \
   : YYSYMBOL_YYUNDEF)

/* YYTRANSLATE[TOKEN-NUM] -- Symbol number corresponding to TOKEN-NUM
   as returned by yylex.  */
static const yytype_int8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
      44,    45,     2,     2,    42,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,    43,    41,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40
};

#if YYDEBUG
/* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
static const yytype_uint8 yyrline[] =
{
       0,    44,    44,    48,    49,    53,    54,    58,    59,    63,
      67,    68,    72,    77,    82,    86,    87,    88,    92,    96,
      97,   101,   105,   106,   110,   111,   112,   113,   114,   115,
     119,   120,   124,   125,   129,   130,   134,   135,   139,   140,
     144,   145,   149,   153,   154,   158,   159,   163,   164,   168,
     169,   173,   174,   178,   179,   180,   184,   185,   189,   190,
     194,   195,   196,   197,   198,   199
};
#endif

/** Accessing symbol of state STATE.  */
#define YY_ACCESSING_SYMBOL(State) YY_CAST (yysymbol_kind_t, yystos[State])

#if 1
/* The user-facing name of the symbol whose (internal) number is
   YYSYMBOL.  No bounds checking.  */
static const char *yysymbol_name (yysymbol_kind_t yysymbol) YY_ATTRIBUTE_UNUSED;

/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "\"end of file\"", "error", "\"invalid token\"", "IDENTIFIER",
  "INT_LITERAL", "REAL_LITERAL", "CHAR_LITERAL", "ADDOP", "MULOP", "MODOP",
  "EXPOP", "NEGOP", "ANDOP", "OROP", "NOTOP", "RELOP", "ARROW", "BEGIN_",
  "CASE", "CHARACTER", "ELSE", "ELSIF", "END", "ENDIF", "ENDFOLD",
  "ENDSWITCH", "FOLD", "FUNCTION", "IF", "INTEGER", "IS", "LEFT", "LIST",
  "OF", "OTHERS", "REAL", "RETURNS", "RIGHT", "SWITCH", "THEN", "WHEN",
  "';'", "','", "':'", "'('", "')'", "$accept", "function",
  "function_header", "optional_parameters", "parameters", "parameter",
  "variables", "variable", "type", "list", "expressions", "body",
  "statement_", "statement", "elsif_clauses", "cases", "case", "direction",
  "operator", "list_choice", "condition", "or_condition", "and_condition",
  "not_condition", "relation", "expression", "term", "power", "unary",
  "primary", YY_NULLPTR
};

static const char *
yysymbol_name (yysymbol_kind_t yysymbol)
{
  return yytname[yysymbol];
}
#endif

#define YYPACT_NINF (-35)

#define yypact_value_is_default(Yyn) \
  ((Yyn) == YYPACT_NINF)

#define YYTABLE_NINF (-7)

#define yytable_value_is_error(Yyn) \
  0

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
static const yytype_int8 yypact[] =
{
     -12,    17,    24,   -35,    33,   -35,    56,    23,    25,    34,
      41,   -35,     5,    27,   -35,   -35,   -35,    77,    77,    84,
      72,    69,    79,    66,   -35,   -35,   -35,    75,    74,    71,
      75,    40,    75,    99,    81,   111,    31,   -35,   113,   -35,
     -35,   -35,   -35,   -35,    83,   -35,    27,    92,    97,   -35,
      75,   -35,   -35,   -35,   106,    71,    71,    87,   115,   117,
     -35,   -35,     3,     7,    89,    91,    15,    93,   -35,    75,
      75,    75,    75,   -35,   -35,    77,    27,    18,   -35,   -35,
       8,   -35,    86,     2,    27,    71,    71,    75,   -35,   -35,
      75,   -35,   -35,    31,   -35,   -35,   -35,   102,   -35,   -35,
     -35,    75,   -35,   112,   -35,   -35,   117,   -35,   111,   -11,
       6,    94,   -26,   111,   -35,    96,    90,   119,   -35,    75,
      98,    75,   -35,    27,    71,   121,   124,    27,   111,   -35,
     111,   118,   103,    27,    27,   120,   -35,    27,   -35,   -35,
     -35,   -35
};

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
static const yytype_int8 yydefact[] =
{
       0,     0,     0,    11,     0,     1,     0,     0,     0,     0,
       5,     8,     0,     0,    10,     2,     4,     0,     0,     0,
       0,     0,     0,    65,    61,    62,    63,     0,     0,     0,
       0,     0,     0,     0,     0,    24,    52,    55,    57,    59,
      17,    15,    16,     9,     0,     7,     0,     0,     0,    23,
       0,    58,    36,    37,     0,     0,     0,     0,    42,    44,
      46,    48,     0,     0,     0,     0,     0,     0,    22,     0,
       0,     0,     0,     3,    14,     0,     0,     0,    38,    39,
       0,    47,     0,     0,     0,     0,     0,     0,    33,    26,
       0,    60,    21,    51,    53,    54,    56,     0,    12,    64,
      41,     0,    40,     0,    49,    31,    43,    45,    50,     0,
       0,     0,     0,    20,    29,     0,     0,     0,    32,     0,
       0,     0,    18,     0,     0,     0,     0,     0,    25,    13,
      19,     0,     0,     0,     0,     0,    28,     0,    35,    34,
      27,    30
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int8 yypgoto[] =
{
     -35,   -35,   -35,   -35,   -35,   125,   -35,   -35,   -13,    32,
     -35,   -35,   -34,   -35,   -35,   -35,   -35,   -35,   -35,   -35,
     -27,   -35,    61,   -20,   -35,   -29,    78,    37,   122,   -35
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int8 yydefgoto[] =
{
       0,     2,     3,     9,    10,    11,     6,    14,    43,   102,
     112,    15,    33,    34,   115,   109,   118,    54,    80,   103,
      57,    58,    59,    60,    61,    35,    36,    37,    38,    39
};

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
static const yytype_int16 yytable[] =
{
      62,    63,    62,    66,    65,    44,    20,   116,    48,    69,
      69,   100,    74,    69,    69,     1,   121,    87,    87,   122,
       4,    77,    69,   117,     5,    69,    62,    83,    22,    82,
      23,    24,    25,    26,     7,    81,     8,    88,    27,    70,
      71,    64,    98,    23,    24,    25,    26,    91,    21,   119,
     105,    27,   101,    28,    55,    29,    62,    62,   108,    12,
      91,   110,    97,    99,    16,    30,   107,    31,    17,    -6,
      18,    32,   113,    13,    23,    24,    25,    26,    23,    24,
      25,    26,    27,    19,    56,    55,    27,     8,    40,   131,
     128,   125,   130,   135,   126,    62,    40,   132,    41,   138,
     139,    47,    46,   141,    42,    52,    41,    94,    95,    96,
      50,    53,    42,    78,    79,    56,   123,   124,    69,    32,
      49,    67,    68,    72,    73,    75,    84,    76,    85,    86,
      89,   104,   111,    90,    92,   127,   114,   133,   101,   129,
     134,   136,   137,   120,    45,   140,   106,    93,     0,    51
};

static const yytype_int16 yycheck[] =
{
      29,    30,    31,    32,    31,    18,     1,    18,    21,     7,
       7,     3,    46,     7,     7,    27,    42,    15,    15,    45,
       3,    50,     7,    34,     0,     7,    55,    56,     1,    56,
       3,     4,     5,     6,     1,    55,     3,    30,    11,     8,
       9,     1,    76,     3,     4,     5,     6,    45,    43,    43,
      84,    11,    44,    26,    14,    28,    85,    86,    87,     3,
      45,    90,    75,    45,    41,    38,    86,    40,    43,    36,
      36,    44,   101,    17,     3,     4,     5,     6,     3,     4,
       5,     6,    11,    42,    44,    14,    11,     3,    19,   123,
     119,     1,   121,   127,     4,   124,    19,   124,    29,   133,
     134,    32,    30,   137,    35,    31,    29,    70,    71,    72,
      44,    37,    35,     7,     8,    44,    20,    21,     7,    44,
      41,    22,    41,    10,    41,    33,    39,    30,    13,    12,
      41,    45,    30,    42,    41,    16,    24,    16,    44,    41,
      16,    23,    39,   111,    19,    25,    85,    69,    -1,    27
};

/* YYSTOS[STATE-NUM] -- The symbol kind of the accessing symbol of
   state STATE-NUM.  */
static const yytype_int8 yystos[] =
{
       0,    27,    47,    48,     3,     0,    52,     1,     3,    49,
      50,    51,     3,    17,    53,    57,    41,    43,    36,    42,
       1,    43,     1,     3,     4,     5,     6,    11,    26,    28,
      38,    40,    44,    58,    59,    71,    72,    73,    74,    75,
      19,    29,    35,    54,    54,    51,    30,    32,    54,    41,
      44,    74,    31,    37,    63,    14,    44,    66,    67,    68,
      69,    70,    71,    71,     1,    66,    71,    22,    41,     7,
       8,     9,    10,    41,    58,    33,    30,    71,     7,     8,
      64,    69,    66,    71,    39,    13,    12,    15,    30,    41,
      42,    45,    41,    72,    73,    73,    73,    54,    58,    45,
       3,    44,    55,    65,    45,    58,    68,    69,    71,    61,
      71,    30,    56,    71,    24,    60,    18,    34,    62,    43,
      55,    42,    45,    20,    21,     1,     4,    16,    71,    41,
      71,    58,    66,    16,    16,    58,    23,    39,    58,    58,
      25,    58
};

/* YYR1[RULE-NUM] -- Symbol kind of the left-hand side of rule RULE-NUM.  */
static const yytype_int8 yyr1[] =
{
       0,    46,    47,    48,    48,    49,    49,    50,    50,    51,
      52,    52,    53,    53,    53,    54,    54,    54,    55,    56,
      56,    57,    58,    58,    59,    59,    59,    59,    59,    59,
      60,    60,    61,    61,    62,    62,    63,    63,    64,    64,
      65,    65,    66,    67,    67,    68,    68,    69,    69,    70,
      70,    71,    71,    72,    72,    72,    73,    73,    74,    74,
      75,    75,    75,    75,    75,    75
};

/* YYR2[RULE-NUM] -- Number of symbols on the right-hand side of rule RULE-NUM.  */
static const yytype_int8 yyr2[] =
{
       0,     2,     3,     6,     4,     1,     0,     3,     1,     3,
       2,     0,     5,     8,     4,     1,     1,     1,     3,     3,
       1,     4,     2,     2,     1,     6,     3,     8,     8,     5,
       5,     0,     2,     0,     4,     4,     1,     1,     1,     1,
       1,     1,     1,     3,     1,     3,     1,     2,     1,     3,
       3,     3,     1,     3,     3,     1,     3,     1,     2,     1,
       3,     1,     1,     1,     4,     1
};


enum { YYENOMEM = -2 };

#define yyerrok         (yyerrstatus = 0)
#define yyclearin       (yychar = YYEMPTY)

#define YYACCEPT        goto yyacceptlab
#define YYABORT         goto yyabortlab
#define YYERROR         goto yyerrorlab
#define YYNOMEM         goto yyexhaustedlab


#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)                                    \
  do                                                              \
    if (yychar == YYEMPTY)                                        \
      {                                                           \
        yychar = (Token);                                         \
        yylval = (Value);                                         \
        YYPOPSTACK (yylen);                                       \
        yystate = *yyssp;                                         \
        goto yybackup;                                            \
      }                                                           \
    else                                                          \
      {                                                           \
        yyerror (YY_("syntax error: cannot back up")); \
        YYERROR;                                                  \
      }                                                           \
  while (0)

/* Backward compatibility with an undocumented macro.
   Use YYerror or YYUNDEF. */
#define YYERRCODE YYUNDEF


/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)                        \
do {                                            \
  if (yydebug)                                  \
    YYFPRINTF Args;                             \
} while (0)




# define YY_SYMBOL_PRINT(Title, Kind, Value, Location)                    \
do {                                                                      \
  if (yydebug)                                                            \
    {                                                                     \
      YYFPRINTF (stderr, "%s ", Title);                                   \
      yy_symbol_print (stderr,                                            \
                  Kind, Value); \
      YYFPRINTF (stderr, "\n");                                           \
    }                                                                     \
} while (0)


/*-----------------------------------.
| Print this symbol's value on YYO.  |
`-----------------------------------*/

static void
yy_symbol_value_print (FILE *yyo,
                       yysymbol_kind_t yykind, YYSTYPE const * const yyvaluep)
{
  FILE *yyoutput = yyo;
  YY_USE (yyoutput);
  if (!yyvaluep)
    return;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YY_USE (yykind);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}


/*---------------------------.
| Print this symbol on YYO.  |
`---------------------------*/

static void
yy_symbol_print (FILE *yyo,
                 yysymbol_kind_t yykind, YYSTYPE const * const yyvaluep)
{
  YYFPRINTF (yyo, "%s %s (",
             yykind < YYNTOKENS ? "token" : "nterm", yysymbol_name (yykind));

  yy_symbol_value_print (yyo, yykind, yyvaluep);
  YYFPRINTF (yyo, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

static void
yy_stack_print (yy_state_t *yybottom, yy_state_t *yytop)
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)                            \
do {                                                            \
  if (yydebug)                                                  \
    yy_stack_print ((Bottom), (Top));                           \
} while (0)


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

static void
yy_reduce_print (yy_state_t *yyssp, YYSTYPE *yyvsp,
                 int yyrule)
{
  int yylno = yyrline[yyrule];
  int yynrhs = yyr2[yyrule];
  int yyi;
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %d):\n",
             yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr,
                       YY_ACCESSING_SYMBOL (+yyssp[yyi + 1 - yynrhs]),
                       &yyvsp[(yyi + 1) - (yynrhs)]);
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)          \
do {                                    \
  if (yydebug)                          \
    yy_reduce_print (yyssp, yyvsp, Rule); \
} while (0)

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args) ((void) 0)
# define YY_SYMBOL_PRINT(Title, Kind, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


/* Context of a parse error.  */
typedef struct
{
  yy_state_t *yyssp;
  yysymbol_kind_t yytoken;
} yypcontext_t;

/* Put in YYARG at most YYARGN of the expected tokens given the
   current YYCTX, and return the number of tokens stored in YYARG.  If
   YYARG is null, return the number of expected tokens (guaranteed to
   be less than YYNTOKENS).  Return YYENOMEM on memory exhaustion.
   Return 0 if there are more than YYARGN expected tokens, yet fill
   YYARG up to YYARGN. */
static int
yypcontext_expected_tokens (const yypcontext_t *yyctx,
                            yysymbol_kind_t yyarg[], int yyargn)
{
  /* Actual size of YYARG. */
  int yycount = 0;
  int yyn = yypact[+*yyctx->yyssp];
  if (!yypact_value_is_default (yyn))
    {
      /* Start YYX at -YYN if negative to avoid negative indexes in
         YYCHECK.  In other words, skip the first -YYN actions for
         this state because they are default actions.  */
      int yyxbegin = yyn < 0 ? -yyn : 0;
      /* Stay within bounds of both yycheck and yytname.  */
      int yychecklim = YYLAST - yyn + 1;
      int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
      int yyx;
      for (yyx = yyxbegin; yyx < yyxend; ++yyx)
        if (yycheck[yyx + yyn] == yyx && yyx != YYSYMBOL_YYerror
            && !yytable_value_is_error (yytable[yyx + yyn]))
          {
            if (!yyarg)
              ++yycount;
            else if (yycount == yyargn)
              return 0;
            else
              yyarg[yycount++] = YY_CAST (yysymbol_kind_t, yyx);
          }
    }
  if (yyarg && yycount == 0 && 0 < yyargn)
    yyarg[0] = YYSYMBOL_YYEMPTY;
  return yycount;
}




#ifndef yystrlen
# if defined __GLIBC__ && defined _STRING_H
#  define yystrlen(S) (YY_CAST (YYPTRDIFF_T, strlen (S)))
# else
/* Return the length of YYSTR.  */
static YYPTRDIFF_T
yystrlen (const char *yystr)
{
  YYPTRDIFF_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
# endif
#endif

#ifndef yystpcpy
# if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#  define yystpcpy stpcpy
# else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
static char *
yystpcpy (char *yydest, const char *yysrc)
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
# endif
#endif

#ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYPTRDIFF_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYPTRDIFF_T yyn = 0;
      char const *yyp = yystr;
      for (;;)
        switch (*++yyp)
          {
          case '\'':
          case ',':
            goto do_not_strip_quotes;

          case '\\':
            if (*++yyp != '\\')
              goto do_not_strip_quotes;
            else
              goto append;

          append:
          default:
            if (yyres)
              yyres[yyn] = *yyp;
            yyn++;
            break;

          case '"':
            if (yyres)
              yyres[yyn] = '\0';
            return yyn;
          }
    do_not_strip_quotes: ;
    }

  if (yyres)
    return yystpcpy (yyres, yystr) - yyres;
  else
    return yystrlen (yystr);
}
#endif


static int
yy_syntax_error_arguments (const yypcontext_t *yyctx,
                           yysymbol_kind_t yyarg[], int yyargn)
{
  /* Actual size of YYARG. */
  int yycount = 0;
  /* There are many possibilities here to consider:
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yyctx->yytoken != YYSYMBOL_YYEMPTY)
    {
      int yyn;
      if (yyarg)
        yyarg[yycount] = yyctx->yytoken;
      ++yycount;
      yyn = yypcontext_expected_tokens (yyctx,
                                        yyarg ? yyarg + 1 : yyarg, yyargn - 1);
      if (yyn == YYENOMEM)
        return YYENOMEM;
      else
        yycount += yyn;
    }
  return yycount;
}

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return -1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return YYENOMEM if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYPTRDIFF_T *yymsg_alloc, char **yymsg,
                const yypcontext_t *yyctx)
{
  enum { YYARGS_MAX = 5 };
  /* Internationalized format string. */
  const char *yyformat = YY_NULLPTR;
  /* Arguments of yyformat: reported tokens (one for the "unexpected",
     one per "expected"). */
  yysymbol_kind_t yyarg[YYARGS_MAX];
  /* Cumulated lengths of YYARG.  */
  YYPTRDIFF_T yysize = 0;

  /* Actual size of YYARG. */
  int yycount = yy_syntax_error_arguments (yyctx, yyarg, YYARGS_MAX);
  if (yycount == YYENOMEM)
    return YYENOMEM;

  switch (yycount)
    {
#define YYCASE_(N, S)                       \
      case N:                               \
        yyformat = S;                       \
        break
    default: /* Avoid compiler warnings. */
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
#undef YYCASE_
    }

  /* Compute error message size.  Don't count the "%s"s, but reserve
     room for the terminator.  */
  yysize = yystrlen (yyformat) - 2 * yycount + 1;
  {
    int yyi;
    for (yyi = 0; yyi < yycount; ++yyi)
      {
        YYPTRDIFF_T yysize1
          = yysize + yytnamerr (YY_NULLPTR, yytname[yyarg[yyi]]);
        if (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM)
          yysize = yysize1;
        else
          return YYENOMEM;
      }
  }

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return -1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yytname[yyarg[yyi++]]);
          yyformat += 2;
        }
      else
        {
          ++yyp;
          ++yyformat;
        }
  }
  return 0;
}


/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

static void
yydestruct (const char *yymsg,
            yysymbol_kind_t yykind, YYSTYPE *yyvaluep)
{
  YY_USE (yyvaluep);
  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yykind, yyvaluep, yylocationp);

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  YY_USE (yykind);
  YY_IGNORE_MAYBE_UNINITIALIZED_END
}


/* Lookahead token kind.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;
/* Number of syntax errors so far.  */
int yynerrs;




/*----------.
| yyparse.  |
`----------*/

int
yyparse (void)
{
    yy_state_fast_t yystate = 0;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus = 0;

    /* Refer to the stacks through separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* Their size.  */
    YYPTRDIFF_T yystacksize = YYINITDEPTH;

    /* The state stack: array, bottom, top.  */
    yy_state_t yyssa[YYINITDEPTH];
    yy_state_t *yyss = yyssa;
    yy_state_t *yyssp = yyss;

    /* The semantic value stack: array, bottom, top.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs = yyvsa;
    YYSTYPE *yyvsp = yyvs;

  int yyn;
  /* The return value of yyparse.  */
  int yyresult;
  /* Lookahead symbol kind.  */
  yysymbol_kind_t yytoken = YYSYMBOL_YYEMPTY;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYPTRDIFF_T yymsg_alloc = sizeof yymsgbuf;

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yychar = YYEMPTY; /* Cause a token to be read.  */

  goto yysetstate;


/*------------------------------------------------------------.
| yynewstate -- push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;


/*--------------------------------------------------------------------.
| yysetstate -- set current state (the top of the stack) to yystate.  |
`--------------------------------------------------------------------*/
yysetstate:
  YYDPRINTF ((stderr, "Entering state %d\n", yystate));
  YY_ASSERT (0 <= yystate && yystate < YYNSTATES);
  YY_IGNORE_USELESS_CAST_BEGIN
  *yyssp = YY_CAST (yy_state_t, yystate);
  YY_IGNORE_USELESS_CAST_END
  YY_STACK_PRINT (yyss, yyssp);

  if (yyss + yystacksize - 1 <= yyssp)
#if !defined yyoverflow && !defined YYSTACK_RELOCATE
    YYNOMEM;
#else
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYPTRDIFF_T yysize = yyssp - yyss + 1;

# if defined yyoverflow
      {
        /* Give user a chance to reallocate the stack.  Use copies of
           these so that the &'s don't force the real ones into
           memory.  */
        yy_state_t *yyss1 = yyss;
        YYSTYPE *yyvs1 = yyvs;

        /* Each stack pointer address is followed by the size of the
           data in use in that stack, in bytes.  This used to be a
           conditional around just the two extra args, but that might
           be undefined if yyoverflow is a macro.  */
        yyoverflow (YY_("memory exhausted"),
                    &yyss1, yysize * YYSIZEOF (*yyssp),
                    &yyvs1, yysize * YYSIZEOF (*yyvsp),
                    &yystacksize);
        yyss = yyss1;
        yyvs = yyvs1;
      }
# else /* defined YYSTACK_RELOCATE */
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
        YYNOMEM;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
        yystacksize = YYMAXDEPTH;

      {
        yy_state_t *yyss1 = yyss;
        union yyalloc *yyptr =
          YY_CAST (union yyalloc *,
                   YYSTACK_ALLOC (YY_CAST (YYSIZE_T, YYSTACK_BYTES (yystacksize))));
        if (! yyptr)
          YYNOMEM;
        YYSTACK_RELOCATE (yyss_alloc, yyss);
        YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
        if (yyss1 != yyssa)
          YYSTACK_FREE (yyss1);
      }
# endif

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YY_IGNORE_USELESS_CAST_BEGIN
      YYDPRINTF ((stderr, "Stack size increased to %ld\n",
                  YY_CAST (long, yystacksize)));
      YY_IGNORE_USELESS_CAST_END

      if (yyss + yystacksize - 1 <= yyssp)
        YYABORT;
    }
#endif /* !defined yyoverflow && !defined YYSTACK_RELOCATE */


  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;


/*-----------.
| yybackup.  |
`-----------*/
yybackup:
  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either empty, or end-of-input, or a valid lookahead.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token\n"));
      yychar = yylex ();
    }

  if (yychar <= YYEOF)
    {
      yychar = YYEOF;
      yytoken = YYSYMBOL_YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else if (yychar == YYerror)
    {
      /* The scanner already issued an error message, process directly
         to error recovery.  But do not keep the error token as
         lookahead, it is too special and may lead us to an endless
         loop in error recovery. */
      yychar = YYUNDEF;
      yytoken = YYSYMBOL_YYerror;
      goto yyerrlab1;
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);
  yystate = yyn;
  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END

  /* Discard the shifted token.  */
  yychar = YYEMPTY;
  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     '$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
  case 2: /* function: function_header variables body  */
#line 44 "parser.y"
                                       { checkAssignment(functionReturnType, (yyvsp[0].type), "Function Return"); }
#line 1516 "parser.tab.c"
    break;

  case 3: /* function_header: FUNCTION IDENTIFIER optional_parameters RETURNS type ';'  */
#line 48 "parser.y"
                                                                 { functionReturnType = (yyvsp[-1].type); }
#line 1522 "parser.tab.c"
    break;

  case 4: /* function_header: FUNCTION IDENTIFIER error ';'  */
#line 49 "parser.y"
                                        { functionReturnType = MISMATCH; yyerrok; }
#line 1528 "parser.tab.c"
    break;

  case 6: /* optional_parameters: %empty  */
#line 54 "parser.y"
                 { (yyval.type) = NONE; }
#line 1534 "parser.tab.c"
    break;

  case 7: /* parameters: parameters ',' parameter  */
#line 58 "parser.y"
                                 { (yyval.type) = NONE; }
#line 1540 "parser.tab.c"
    break;

  case 8: /* parameters: parameter  */
#line 59 "parser.y"
                    { (yyval.type) = NONE; }
#line 1546 "parser.tab.c"
    break;

  case 9: /* parameter: IDENTIFIER ':' type  */
#line 63 "parser.y"
                            { insertScalar((yyvsp[-2].iden), (yyvsp[0].type)); (yyval.type) = (yyvsp[0].type); }
#line 1552 "parser.tab.c"
    break;

  case 10: /* variables: variables variable  */
#line 67 "parser.y"
                           { (yyval.type) = NONE; }
#line 1558 "parser.tab.c"
    break;

  case 11: /* variables: %empty  */
#line 68 "parser.y"
                 { (yyval.type) = NONE; }
#line 1564 "parser.tab.c"
    break;

  case 12: /* variable: IDENTIFIER ':' type IS statement_  */
#line 72 "parser.y"
                                          {
		checkAssignment((yyvsp[-2].type), (yyvsp[0].type), "Variable Initialization");
		insertScalar((yyvsp[-4].iden), (yyvsp[-2].type));
		(yyval.type) = (yyvsp[-2].type);
	}
#line 1574 "parser.tab.c"
    break;

  case 13: /* variable: IDENTIFIER ':' LIST OF type IS list ';'  */
#line 77 "parser.y"
                                                  {
		checkListType((yyvsp[-3].type), (yyvsp[-1].type));
		insertList((yyvsp[-7].iden), (yyvsp[-3].type));
		(yyval.type) = NONE;
	}
#line 1584 "parser.tab.c"
    break;

  case 14: /* variable: IDENTIFIER error IS statement_  */
#line 82 "parser.y"
                                         { (yyval.type) = MISMATCH; yyerrok; }
#line 1590 "parser.tab.c"
    break;

  case 15: /* type: INTEGER  */
#line 86 "parser.y"
                { (yyval.type) = INT_TYPE; }
#line 1596 "parser.tab.c"
    break;

  case 16: /* type: REAL  */
#line 87 "parser.y"
               { (yyval.type) = REAL_TYPE; }
#line 1602 "parser.tab.c"
    break;

  case 17: /* type: CHARACTER  */
#line 88 "parser.y"
                    { (yyval.type) = CHAR_TYPE; }
#line 1608 "parser.tab.c"
    break;

  case 18: /* list: '(' expressions ')'  */
#line 92 "parser.y"
                            { (yyval.type) = (yyvsp[-1].type); }
#line 1614 "parser.tab.c"
    break;

  case 19: /* expressions: expressions ',' expression  */
#line 96 "parser.y"
                                   { (yyval.type) = checkListElements((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1620 "parser.tab.c"
    break;

  case 20: /* expressions: expression  */
#line 97 "parser.y"
                     { (yyval.type) = (yyvsp[0].type); }
#line 1626 "parser.tab.c"
    break;

  case 21: /* body: BEGIN_ statement_ END ';'  */
#line 101 "parser.y"
                                  { (yyval.type) = (yyvsp[-2].type); }
#line 1632 "parser.tab.c"
    break;

  case 22: /* statement_: statement ';'  */
#line 105 "parser.y"
                      { (yyval.type) = (yyvsp[-1].type); }
#line 1638 "parser.tab.c"
    break;

  case 23: /* statement_: error ';'  */
#line 106 "parser.y"
                    { (yyval.type) = MISMATCH; yyerrok; }
#line 1644 "parser.tab.c"
    break;

  case 24: /* statement: expression  */
#line 110 "parser.y"
                   { (yyval.type) = (yyvsp[0].type); }
#line 1650 "parser.tab.c"
    break;

  case 25: /* statement: WHEN condition ',' expression ':' expression  */
#line 111 "parser.y"
                                                       { (yyval.type) = checkWhen((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1656 "parser.tab.c"
    break;

  case 26: /* statement: WHEN error ';'  */
#line 112 "parser.y"
                         { (yyval.type) = MISMATCH; yyerrok; }
#line 1662 "parser.tab.c"
    break;

  case 27: /* statement: SWITCH expression IS cases OTHERS ARROW statement_ ENDSWITCH  */
#line 113 "parser.y"
                                                                       { (yyval.type) = checkSwitch((yyvsp[-6].type), (yyvsp[-4].type), (yyvsp[-1].type)); }
#line 1668 "parser.tab.c"
    break;

  case 28: /* statement: IF condition THEN statement_ elsif_clauses ELSE statement_ ENDIF  */
#line 114 "parser.y"
                                                                           { (yyval.type) = checkIf((yyvsp[-4].type), (yyvsp[-3].type), (yyvsp[-1].type)); }
#line 1674 "parser.tab.c"
    break;

  case 29: /* statement: FOLD direction operator list_choice ENDFOLD  */
#line 115 "parser.y"
                                                      { (yyval.type) = checkFold((yyvsp[-1].type)); }
#line 1680 "parser.tab.c"
    break;

  case 30: /* elsif_clauses: elsif_clauses ELSIF condition THEN statement_  */
#line 119 "parser.y"
                                                      { (yyval.type) = checkCases((yyvsp[-4].type), (yyvsp[0].type)); }
#line 1686 "parser.tab.c"
    break;

  case 31: /* elsif_clauses: %empty  */
#line 120 "parser.y"
                 { (yyval.type) = NONE; }
#line 1692 "parser.tab.c"
    break;

  case 32: /* cases: cases case  */
#line 124 "parser.y"
                   { (yyval.type) = checkCases((yyvsp[-1].type), (yyvsp[0].type)); }
#line 1698 "parser.tab.c"
    break;

  case 33: /* cases: %empty  */
#line 125 "parser.y"
                 { (yyval.type) = NONE; }
#line 1704 "parser.tab.c"
    break;

  case 34: /* case: CASE INT_LITERAL ARROW statement_  */
#line 129 "parser.y"
                                          { (yyval.type) = (yyvsp[0].type); }
#line 1710 "parser.tab.c"
    break;

  case 35: /* case: CASE error ARROW statement_  */
#line 130 "parser.y"
                                      { (yyval.type) = MISMATCH; yyerrok; }
#line 1716 "parser.tab.c"
    break;

  case 36: /* direction: LEFT  */
#line 134 "parser.y"
             { (yyval.type) = NONE; }
#line 1722 "parser.tab.c"
    break;

  case 37: /* direction: RIGHT  */
#line 135 "parser.y"
                { (yyval.type) = NONE; }
#line 1728 "parser.tab.c"
    break;

  case 38: /* operator: ADDOP  */
#line 139 "parser.y"
              { (yyval.type) = NONE; }
#line 1734 "parser.tab.c"
    break;

  case 39: /* operator: MULOP  */
#line 140 "parser.y"
                { (yyval.type) = NONE; }
#line 1740 "parser.tab.c"
    break;

  case 40: /* list_choice: list  */
#line 144 "parser.y"
             { (yyval.type) = (yyvsp[0].type); }
#line 1746 "parser.tab.c"
    break;

  case 41: /* list_choice: IDENTIFIER  */
#line 145 "parser.y"
                     { (yyval.type) = find(lists, (yyvsp[0].iden), "List "); }
#line 1752 "parser.tab.c"
    break;

  case 42: /* condition: or_condition  */
#line 149 "parser.y"
                     { (yyval.type) = (yyvsp[0].type); }
#line 1758 "parser.tab.c"
    break;

  case 43: /* or_condition: or_condition OROP and_condition  */
#line 153 "parser.y"
                                        { (yyval.type) = INT_TYPE; }
#line 1764 "parser.tab.c"
    break;

  case 44: /* or_condition: and_condition  */
#line 154 "parser.y"
                        { (yyval.type) = (yyvsp[0].type); }
#line 1770 "parser.tab.c"
    break;

  case 45: /* and_condition: and_condition ANDOP not_condition  */
#line 158 "parser.y"
                                          { (yyval.type) = INT_TYPE; }
#line 1776 "parser.tab.c"
    break;

  case 46: /* and_condition: not_condition  */
#line 159 "parser.y"
                        { (yyval.type) = (yyvsp[0].type); }
#line 1782 "parser.tab.c"
    break;

  case 47: /* not_condition: NOTOP not_condition  */
#line 163 "parser.y"
                            { (yyval.type) = (yyvsp[0].type); }
#line 1788 "parser.tab.c"
    break;

  case 48: /* not_condition: relation  */
#line 164 "parser.y"
                   { (yyval.type) = (yyvsp[0].type); }
#line 1794 "parser.tab.c"
    break;

  case 49: /* relation: '(' condition ')'  */
#line 168 "parser.y"
                          { (yyval.type) = (yyvsp[-1].type); }
#line 1800 "parser.tab.c"
    break;

  case 50: /* relation: expression RELOP expression  */
#line 169 "parser.y"
                                      { (yyval.type) = checkRelational((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1806 "parser.tab.c"
    break;

  case 51: /* expression: expression ADDOP term  */
#line 173 "parser.y"
                              { (yyval.type) = checkArithmetic((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1812 "parser.tab.c"
    break;

  case 52: /* expression: term  */
#line 174 "parser.y"
               { (yyval.type) = (yyvsp[0].type); }
#line 1818 "parser.tab.c"
    break;

  case 53: /* term: term MULOP power  */
#line 178 "parser.y"
                         { (yyval.type) = checkArithmetic((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1824 "parser.tab.c"
    break;

  case 54: /* term: term MODOP power  */
#line 179 "parser.y"
                           { (yyval.type) = checkRemainder((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1830 "parser.tab.c"
    break;

  case 55: /* term: power  */
#line 180 "parser.y"
                { (yyval.type) = (yyvsp[0].type); }
#line 1836 "parser.tab.c"
    break;

  case 56: /* power: unary EXPOP power  */
#line 184 "parser.y"
                          { (yyval.type) = checkArithmetic((yyvsp[-2].type), (yyvsp[0].type)); }
#line 1842 "parser.tab.c"
    break;

  case 57: /* power: unary  */
#line 185 "parser.y"
                { (yyval.type) = (yyvsp[0].type); }
#line 1848 "parser.tab.c"
    break;

  case 58: /* unary: NEGOP unary  */
#line 189 "parser.y"
                    { (yyval.type) = checkUnaryNumeric((yyvsp[0].type)); }
#line 1854 "parser.tab.c"
    break;

  case 59: /* unary: primary  */
#line 190 "parser.y"
                  { (yyval.type) = (yyvsp[0].type); }
#line 1860 "parser.tab.c"
    break;

  case 60: /* primary: '(' expression ')'  */
#line 194 "parser.y"
                           { (yyval.type) = (yyvsp[-1].type); }
#line 1866 "parser.tab.c"
    break;

  case 61: /* primary: INT_LITERAL  */
#line 195 "parser.y"
                      { (yyval.type) = (yyvsp[0].type); }
#line 1872 "parser.tab.c"
    break;

  case 62: /* primary: REAL_LITERAL  */
#line 196 "parser.y"
                       { (yyval.type) = (yyvsp[0].type); }
#line 1878 "parser.tab.c"
    break;

  case 63: /* primary: CHAR_LITERAL  */
#line 197 "parser.y"
                       { (yyval.type) = (yyvsp[0].type); }
#line 1884 "parser.tab.c"
    break;

  case 64: /* primary: IDENTIFIER '(' expression ')'  */
#line 198 "parser.y"
                                        { (yyval.type) = checkListSubscript((yyvsp[-1].type), find(lists, (yyvsp[-3].iden), "List ")); }
#line 1890 "parser.tab.c"
    break;

  case 65: /* primary: IDENTIFIER  */
#line 199 "parser.y"
                     { (yyval.type) = find(scalars, (yyvsp[0].iden), "Scalar "); }
#line 1896 "parser.tab.c"
    break;


#line 1900 "parser.tab.c"

      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", YY_CAST (yysymbol_kind_t, yyr1[yyn]), &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;

  *++yyvsp = yyval;

  /* Now 'shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */
  {
    const int yylhs = yyr1[yyn] - YYNTOKENS;
    const int yyi = yypgoto[yylhs] + *yyssp;
    yystate = (0 <= yyi && yyi <= YYLAST && yycheck[yyi] == *yyssp
               ? yytable[yyi]
               : yydefgoto[yylhs]);
  }

  goto yynewstate;


/*--------------------------------------.
| yyerrlab -- here on detecting error.  |
`--------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYSYMBOL_YYEMPTY : YYTRANSLATE (yychar);
  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
      {
        yypcontext_t yyctx
          = {yyssp, yytoken};
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = yysyntax_error (&yymsg_alloc, &yymsg, &yyctx);
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == -1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = YY_CAST (char *,
                             YYSTACK_ALLOC (YY_CAST (YYSIZE_T, yymsg_alloc)));
            if (yymsg)
              {
                yysyntax_error_status
                  = yysyntax_error (&yymsg_alloc, &yymsg, &yyctx);
                yymsgp = yymsg;
              }
            else
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = YYENOMEM;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == YYENOMEM)
          YYNOMEM;
      }
    }

  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

      if (yychar <= YYEOF)
        {
          /* Return failure if at end of input.  */
          if (yychar == YYEOF)
            YYABORT;
        }
      else
        {
          yydestruct ("Error: discarding",
                      yytoken, &yylval);
          yychar = YYEMPTY;
        }
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:
  /* Pacify compilers when the user code never invokes YYERROR and the
     label yyerrorlab therefore never appears in user code.  */
  if (0)
    YYERROR;
  ++yynerrs;

  /* Do not reclaim the symbols of the rule whose action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;      /* Each real token shifted decrements this.  */

  /* Pop stack until we find a state that shifts the error token.  */
  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
        {
          yyn += YYSYMBOL_YYerror;
          if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYSYMBOL_YYerror)
            {
              yyn = yytable[yyn];
              if (0 < yyn)
                break;
            }
        }

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
        YYABORT;


      yydestruct ("Error: popping",
                  YY_ACCESSING_SYMBOL (yystate), yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  YY_IGNORE_MAYBE_UNINITIALIZED_BEGIN
  *++yyvsp = yylval;
  YY_IGNORE_MAYBE_UNINITIALIZED_END


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", YY_ACCESSING_SYMBOL (yyn), yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturnlab;


/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturnlab;


/*-----------------------------------------------------------.
| yyexhaustedlab -- YYNOMEM (memory exhaustion) comes here.  |
`-----------------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  goto yyreturnlab;


/*----------------------------------------------------------.
| yyreturnlab -- parsing is finished, clean up and return.  |
`----------------------------------------------------------*/
yyreturnlab:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule whose action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
                  YY_ACCESSING_SYMBOL (+*yyssp), yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
  return yyresult;
}

#line 202 "parser.y"


Types find(Symbols<Types>& table, CharPtr identifier, string kind) {
	Types type;
	if (!table.find(identifier, type)) {
		appendError(UNDECLARED, kind + identifier);
		return MISMATCH;
	}
	return type;
}

void insertScalar(CharPtr identifier, Types type) {
	Types existing;
	if (scalars.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "Scalar " + string(identifier));
		return;
	}
	if (lists.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "Scalar " + string(identifier));
		return;
	}
	scalars.insert(identifier, type);
}

void insertList(CharPtr identifier, Types type) {
	Types existing;
	if (lists.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "List " + string(identifier));
		return;
	}
	if (scalars.find(identifier, existing)) {
		appendError(DUPLICATE_IDENTIFIER, "List " + string(identifier));
		return;
	}
	lists.insert(identifier, type);
}

void yyerror(const char* message) {
	string formatted = message;
	size_t unexpected = formatted.find("unexpected ");
	if (unexpected != string::npos) {
		formatted = formatted.substr(unexpected);
	}
	appendError(SYNTAX, formatted);
}

int main(int argc, char *argv[]) {
	firstLine();
	yyparse();
	lastLine();
	return 0;
}
