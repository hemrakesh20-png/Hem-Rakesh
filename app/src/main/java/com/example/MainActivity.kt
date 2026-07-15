package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.icons.rounded.AppShortcut
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

// Design Styles enum for custom generator
enum class DesignStyle(val label: String, val desc: String) {
  CYBERPUNK("Cyberpunk Glow", "Neon purple and electric blue geometric vibes"),
  MINIMALIST("Golden Luxury", "Sophisticated fine gold lines on clean charcoal"),
  RETRO("vintage Art", "Warm sunset tones with organic classic typography"),
  SWISS("Modern Swiss", "Bold grid, bright red contrast and solid geometry")
}

// Design Type: Poster or Logo
enum class DesignType(val label: String) {
  POSTER("Poster"),
  LOGO("Logo")
}

enum class BrandIcon(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
  PALETTE("Art Palette", androidx.compose.material.icons.Icons.Default.Palette),
  STAR("Classic Star", androidx.compose.material.icons.Icons.Default.Star),
  BRUSH("Design Brush", androidx.compose.material.icons.Icons.Default.Brush),
  COLOR_LENS("Color Wheel", androidx.compose.material.icons.Icons.Default.ColorLens),
  SPEED("High Speed", androidx.compose.material.icons.Icons.Default.Speed),
  TAG("Tag Promo", androidx.compose.material.icons.Icons.Default.Sell),
  ACTIVITY("Ticket Badge", androidx.compose.material.icons.Icons.Default.LocalActivity),
  APP_SHORTCUT("Tech Node", androidx.compose.material.icons.Icons.Rounded.AppShortcut)
}

enum class BrandColorScheme(val label: String, val primary: Color, val secondary: Color) {
  CYBER_GLOW("Cyber Glow", Color(0xFFA78BFA), Color(0xFF60A5FA)),
  GOLDEN_LUXE("Golden Luxe", Color(0xFFFCD34D), Color(0xFFF59E0B)),
  SUNSET_BLAZE("Sunset Blaze", Color(0xFFF43F5E), Color(0xFFFB923C)),
  EMERALD_NEON("Emerald Neon", Color(0xFF34D399), Color(0xFF059669)),
  MONO_MINIMAL("Mono Minimal", Color.White, Color(0xFF94A3B8))
}

enum class BrandFontStyle(val label: String, val fontFamily: androidx.compose.ui.text.font.FontFamily) {
  SANS("Modern Sans", androidx.compose.ui.text.font.FontFamily.SansSerif),
  SERIF("Classic Serif", androidx.compose.ui.text.font.FontFamily.Serif),
  MONO("Tech Mono", androidx.compose.ui.text.font.FontFamily.Monospace),
  CURSIVE("Creative Script", androidx.compose.ui.text.font.FontFamily.Cursive)
}

enum class BrandLayout(val label: String) {
  ICON_TOP("Icon on Top"),
  ICON_LEFT("Icon on Left"),
  TEXT_ONLY("Text Only")
}

data class SavedDesign(
  val id: String,
  val title: String,
  val type: DesignType,
  val style: DesignStyle,
  val slogan: String,
  val icon: BrandIcon,
  val colorScheme: BrandColorScheme,
  val fontStyle: BrandFontStyle,
  val fontSize: Float,
  val layout: BrandLayout
) {
  fun toPersistableString(): String {
    return "$id|$title|${type.name}|${style.name}|$slogan|${icon.name}|${colorScheme.name}|${fontStyle.name}|$fontSize|${layout.name}"
  }

  companion object {
    fun fromPersistableString(str: String): SavedDesign? {
      return try {
        val parts = str.split("|")
        if (parts.size < 10) return null
        SavedDesign(
          id = parts[0],
          title = parts[1],
          type = DesignType.valueOf(parts[2]),
          style = DesignStyle.valueOf(parts[3]),
          slogan = parts[4],
          icon = BrandIcon.valueOf(parts[5]),
          colorScheme = BrandColorScheme.valueOf(parts[6]),
          fontStyle = BrandFontStyle.valueOf(parts[7]),
          fontSize = parts[8].toFloat(),
          layout = BrandLayout.valueOf(parts[9])
        )
      } catch (e: Exception) {
        null
      }
    }
  }
}

fun exportBitmapAndSaveOrShare(
  context: android.content.Context,
  title: String,
  type: DesignType,
  style: DesignStyle,
  slogan: String,
  icon: BrandIcon,
  colorScheme: BrandColorScheme,
  fontStyle: BrandFontStyle,
  fontSize: Float,
  layout: BrandLayout,
  shareAfterSave: Boolean
) {
  try {
    val width = if (type == DesignType.POSTER) 800 else 600
    val height = if (type == DesignType.POSTER) 1100 else 600
    val bitmap = android.graphics.Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)

    // Background color
    val bgColor = when (style) {
      DesignStyle.CYBERPUNK -> 0xFF0F172A.toInt()
      DesignStyle.MINIMALIST -> 0xFF1C1917.toInt()
      DesignStyle.RETRO -> 0xFF451A03.toInt()
      DesignStyle.SWISS -> 0xFF0F172A.toInt()
    }
    canvas.drawColor(bgColor)

    val paint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
      this.style = android.graphics.Paint.Style.FILL
    }

    // Border
    paint.color = colorScheme.primary.toArgb()
    paint.style = android.graphics.Paint.Style.STROKE
    paint.strokeWidth = 10f
    val margin = 20f
    canvas.drawRect(margin, margin, width - margin, height - margin, paint)

    // Center geometry / decorative
    paint.style = android.graphics.Paint.Style.FILL
    val centerX = width / 2f
    val centerY = if (type == DesignType.POSTER) height / 2.2f else height / 2.5f

    // Draw some stylized background geometry depending on style
    paint.color = colorScheme.secondary.toArgb()
    paint.alpha = 50 // transparent
    canvas.drawCircle(centerX, centerY, width / 4f, paint)
    paint.alpha = 255

    // Draw title and slogan
    val typeface = when (fontStyle) {
      BrandFontStyle.SANS -> android.graphics.Typeface.SANS_SERIF
      BrandFontStyle.SERIF -> android.graphics.Typeface.SERIF
      BrandFontStyle.MONO -> android.graphics.Typeface.MONOSPACE
      BrandFontStyle.CURSIVE -> android.graphics.Typeface.create("sans-serif-light", android.graphics.Typeface.ITALIC)
    }

    // Draw main Brand Title text
    val textPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
      color = colorScheme.primary.toArgb()
      setTypeface(android.graphics.Typeface.create(typeface, android.graphics.Typeface.BOLD))
      textSize = fontSize * (width / 300f) // scale text size according to export resolution
      textAlign = android.graphics.Paint.Align.CENTER
    }

    val textY = if (type == DesignType.POSTER) height - 250f else centerY + 150f
    val cleanTitle = if (title.isBlank()) "ACME CORP" else title.uppercase()
    canvas.drawText(cleanTitle, centerX, textY, textPaint)

    // Draw tagline/slogan text
    val sloganPaint = android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
      color = colorScheme.secondary.toArgb()
      setTypeface(android.graphics.Typeface.create(typeface, android.graphics.Typeface.NORMAL))
      textSize = fontSize * 0.45f * (width / 300f)
      textAlign = android.graphics.Paint.Align.CENTER
    }
    canvas.drawText(slogan.uppercase(), centerX, textY + 80f, sloganPaint)

    // Save to MediaStore (Pictures gallery)
    val resolver = context.contentResolver
    val contentValues = android.content.ContentValues().apply {
      put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, "BrandCraft_${System.currentTimeMillis()}.png")
      put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "image/png")
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_PICTURES + "/BrandCraft")
        put(android.provider.MediaStore.MediaColumns.IS_PENDING, 1)
      }
    }

    val imageUri = resolver.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    if (imageUri != null) {
      resolver.openOutputStream(imageUri).use { out ->
        if (out != null) {
          bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, out)
        }
      }

      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
        contentValues.clear()
        contentValues.put(android.provider.MediaStore.MediaColumns.IS_PENDING, 0)
        resolver.update(imageUri, contentValues, null, null)
      }

      android.widget.Toast.makeText(context, "Design saved to Pictures/BrandCraft!", android.widget.Toast.LENGTH_LONG).show()

      if (shareAfterSave) {
        val shareIntent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
          this.type = "image/png"
          putExtra(android.content.Intent.EXTRA_STREAM, imageUri)
          putExtra(android.content.Intent.EXTRA_SUBJECT, "My Custom Brand Logo/Poster")
          putExtra(android.content.Intent.EXTRA_TEXT, "Created this gorgeous $type '$cleanTitle' using BrandCraft!")
          addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(android.content.Intent.createChooser(shareIntent, "Share Design via"))
      }
    }
  } catch (e: Exception) {
    e.printStackTrace()
    android.widget.Toast.makeText(context, "Failed to save design: ${e.localizedMessage}", android.widget.Toast.LENGTH_SHORT).show()
  }
}

fun saveOrShareGeneratedImage(
  context: android.content.Context,
  imageUrl: String,
  title: String,
  shareAfterSave: Boolean,
  onProgressChange: (Boolean) -> Unit = {}
) {
  onProgressChange(true)
  val client = okhttp3.OkHttpClient.Builder()
    .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
    .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
    .build()
  val request = okhttp3.Request.Builder().url(imageUrl).build()

  kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
    try {
      val response = client.newCall(request).execute()
      if (!response.isSuccessful) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
          onProgressChange(false)
          android.widget.Toast.makeText(context, "Failed to download image: ${response.message}", android.widget.Toast.LENGTH_SHORT).show()
        }
        return@launch
      }

      val bytes = response.body?.bytes()
      if (bytes == null) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
          onProgressChange(false)
          android.widget.Toast.makeText(context, "Failed to download image bytes", android.widget.Toast.LENGTH_SHORT).show()
        }
        return@launch
      }

      val bitmap = android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
      if (bitmap == null) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
          onProgressChange(false)
          android.widget.Toast.makeText(context, "Failed to decode downloaded image", android.widget.Toast.LENGTH_SHORT).show()
        }
        return@launch
      }

      kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
        onProgressChange(false)
        val resolver = context.contentResolver
        val contentValues = android.content.ContentValues().apply {
          put(android.provider.MediaStore.MediaColumns.DISPLAY_NAME, "AI_Brand_${title.replace("[^a-zA-Z0-9]".toRegex(), "_")}_${System.currentTimeMillis()}.png")
          put(android.provider.MediaStore.MediaColumns.MIME_TYPE, "image/png")
          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            put(android.provider.MediaStore.MediaColumns.RELATIVE_PATH, android.os.Environment.DIRECTORY_PICTURES + "/BrandCraft")
            put(android.provider.MediaStore.MediaColumns.IS_PENDING, 1)
          }
        }

        val imageUri = resolver.insert(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        if (imageUri != null) {
          resolver.openOutputStream(imageUri).use { out ->
            if (out != null) {
              bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, out)
            }
          }

          if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            contentValues.clear()
            contentValues.put(android.provider.MediaStore.MediaColumns.IS_PENDING, 0)
            resolver.update(imageUri, contentValues, null, null)
          }

          android.widget.Toast.makeText(context, "AI Image saved to Pictures/BrandCraft!", android.widget.Toast.LENGTH_LONG).show()

          if (shareAfterSave) {
            val shareIntent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
              this.type = "image/png"
              putExtra(android.content.Intent.EXTRA_STREAM, imageUri)
              putExtra(android.content.Intent.EXTRA_SUBJECT, "AI Generated Brand Logo/Poster")
              putExtra(android.content.Intent.EXTRA_TEXT, "Generated this gorgeous AI visual for '$title' using Gemini!")
              addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(android.content.Intent.createChooser(shareIntent, "Share Image via"))
          }
        }
      }
    } catch (e: Exception) {
      e.printStackTrace()
      kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
        onProgressChange(false)
        android.widget.Toast.makeText(context, "Failed to save AI image: ${e.localizedMessage}", android.widget.Toast.LENGTH_SHORT).show()
      }
    }
  }
}

data class RecentLogo(
  val id: String,
  val brandName: String,
  val industry: String,
  val prompt: String,
  val imageUrl: String,
  val timestamp: Long
) {
  fun toPersistableString(): String {
    val escapedBrand = brandName.replace("|", "~")
    val escapedIndustry = industry.replace("|", "~")
    val escapedPrompt = prompt.replace("|", "~")
    return "$id|$escapedBrand|$escapedIndustry|$escapedPrompt|$imageUrl|$timestamp"
  }

  companion object {
    fun fromPersistableString(str: String): RecentLogo? {
      return try {
        val parts = str.split("|")
        if (parts.size < 6) return null
        RecentLogo(
          id = parts[0],
          brandName = parts[1].replace("~", "|"),
          industry = parts[2].replace("~", "|"),
          prompt = parts[3].replace("~", "|"),
          imageUrl = parts[4],
          timestamp = parts[5].toLong()
        )
      } catch (e: Exception) {
        null
      }
    }
  }
}

fun getRecentLogos(context: android.content.Context): List<RecentLogo> {
  val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
  val raw = prefs.getString("recent_logos_data", "") ?: ""
  if (raw.isEmpty()) return emptyList()
  return raw.split("\n").mapNotNull { RecentLogo.fromPersistableString(it) }
}

fun saveRecentLogo(context: android.content.Context, logo: RecentLogo): List<RecentLogo> {
  val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
  val current = getRecentLogos(context).toMutableList()
  current.removeAll { it.imageUrl == logo.imageUrl }
  current.add(0, logo)
  val trimmed = current.take(5)
  val serialized = trimmed.joinToString("\n") { it.toPersistableString() }
  prefs.edit().putString("recent_logos_data", serialized).apply()
  return trimmed
}


data class SavedAiDesign(
  val id: String,
  val prompt: String,
  val title: String,
  val imageUrl: String,
  val cloudUrl: String?,
  val timestamp: Long
) {
  fun toPersistableString(): String {
    val escapedPrompt = prompt.replace("|", "~")
    val escapedTitle = title.replace("|", "~")
    return "$id|$escapedPrompt|$escapedTitle|$imageUrl|${cloudUrl ?: ""}|$timestamp"
  }

  companion object {
    fun fromPersistableString(str: String): SavedAiDesign? {
      return try {
        val parts = str.split("|")
        if (parts.size < 6) return null
        SavedAiDesign(
          id = parts[0],
          prompt = parts[1].replace("~", "|"),
          title = parts[2].replace("~", "|"),
          imageUrl = parts[3],
          cloudUrl = parts[4].ifEmpty { null },
          timestamp = parts[5].toLong()
        )
      } catch (e: Exception) {
        null
      }
    }
  }
}

fun uploadImageToFirebaseStorage(
  context: android.content.Context,
  imageUrl: String,
  title: String,
  onProgressChange: (Boolean) -> Unit,
  onComplete: (String?, String?) -> Unit
) {
  onProgressChange(true)
  val client = okhttp3.OkHttpClient.Builder()
    .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
    .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
    .build()
  val request = okhttp3.Request.Builder().url(imageUrl).build()

  kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
    try {
      val response = client.newCall(request).execute()
      if (!response.isSuccessful) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
          onProgressChange(false)
          onComplete(null, "Failed to download image bytes: ${response.message}")
        }
        return@launch
      }

      val bytes = response.body?.bytes()
      if (bytes == null) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
          onProgressChange(false)
          onComplete(null, "Failed to download image bytes: body is empty")
        }
        return@launch
      }

      // Check if Firebase is initialized
      if (com.google.firebase.FirebaseApp.getApps(context).isEmpty()) {
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
          onProgressChange(false)
          onComplete(null, "Firebase is not initialized. Please ensure your google-services.json file is added to /app directory.")
        }
        return@launch
      }

      val storage = com.google.firebase.storage.FirebaseStorage.getInstance()
      val filename = "designs/ai_design_${System.currentTimeMillis()}_${title.replace("[^a-zA-Z0-9]".toRegex(), "_")}.png"
      val storageRef = storage.reference.child(filename)

      val uploadTask = storageRef.putBytes(bytes)
      uploadTask.addOnSuccessListener {
        storageRef.downloadUrl.addOnSuccessListener { uri ->
          kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            onProgressChange(false)
            onComplete(uri.toString(), null)
          }
        }.addOnFailureListener { e ->
          kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            onProgressChange(false)
            onComplete(null, "Failed to retrieve cloud download URL: ${e.localizedMessage}")
          }
        }
      }.addOnFailureListener { e ->
        kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
          onProgressChange(false)
          onComplete(null, "Firebase Storage upload failed: ${e.localizedMessage}")
        }
      }

    } catch (e: Exception) {
      e.printStackTrace()
      kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
        onProgressChange(false)
        onComplete(null, "Error: ${e.localizedMessage}")
      }
    }
  }
}


class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyApplicationTheme(dynamicColor = false) {
        LandingPageScreen()
      }
    }
  }
}

@Composable
fun LandingPageScreen() {
  val scrollState = rememberScrollState()
  val coroutineScope = rememberCoroutineScope()

  // Track coordinates of sections to implement clean anchor navigation (smooth scroll)
  var featuresOffset by remember { mutableFloatStateOf(0f) }
  var galleryOffset by remember { mutableFloatStateOf(0f) }
  var pricingOffset by remember { mutableFloatStateOf(0f) }
  var faqOffset by remember { mutableFloatStateOf(0f) }

  // Shared Form / Dialog States for Conversions
  var showTrialDialog by remember { mutableStateOf(false) }
  var showUpiPaymentDialog by remember { mutableStateOf(false) }
  var selectedPaymentTier by remember { mutableStateOf("") }
  var selectedPaymentPrice by remember { mutableStateOf("") }
  var isPremiumUser by remember { mutableStateOf(false) }
  var isLoggedIn by remember { mutableStateOf(false) }
  var loggedInEmail by remember { mutableStateOf("") }
  var showLoginDialog by remember { mutableStateOf(false) }
  var showDownloadSuccessDialog by remember { mutableStateOf(false) }
  var lastDownloadedTitle by remember { mutableStateOf("") }
  var lastDownloadedType by remember { mutableStateOf("") }

  // Customizable Generator States (The main interactive element)
  val context = LocalContext.current
  var brandNameInput by remember { mutableStateOf("Acme Corp") }
  var sloganInput by remember { mutableStateOf("CREATIVE LABS") }
  var selectedType by remember { mutableStateOf(DesignType.LOGO) }
  var selectedStyle by remember { mutableStateOf(DesignStyle.CYBERPUNK) }
  var selectedIcon by remember { mutableStateOf(BrandIcon.PALETTE) }
  var selectedColorScheme by remember { mutableStateOf(BrandColorScheme.CYBER_GLOW) }
  var selectedFontStyle by remember { mutableStateOf(BrandFontStyle.SANS) }
  var selectedLayout by remember { mutableStateOf(BrandLayout.ICON_TOP) }
  var fontSize by remember { mutableStateOf(24f) }

  var savedDesigns by remember { mutableStateOf<List<SavedDesign>>(emptyList()) }
  var savedAiDesigns by remember { mutableStateOf<List<SavedAiDesign>>(emptyList()) }

  LaunchedEffect(Unit) {
    val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
    isLoggedIn = prefs.getBoolean("is_logged_in", false)
    loggedInEmail = prefs.getString("logged_in_email", "") ?: ""
    isPremiumUser = prefs.getBoolean("is_premium_user", false)

    val designStrings = prefs.getStringSet("saved_designs_set", emptySet()) ?: emptySet()
    savedDesigns = designStrings.mapNotNull { SavedDesign.fromPersistableString(it) }.sortedByDescending { it.id }

    val aiStrings = prefs.getStringSet("saved_ai_designs_set", emptySet()) ?: emptySet()
    savedAiDesigns = aiStrings.mapNotNull { SavedAiDesign.fromPersistableString(it) }.sortedByDescending { it.id }
  }

  val saveDesignToLibrary = {
    val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
    val currentSet = prefs.getStringSet("saved_designs_set", emptySet()) ?: emptySet()
    val newDesign = SavedDesign(
      id = System.currentTimeMillis().toString(),
      title = brandNameInput,
      type = selectedType,
      style = selectedStyle,
      slogan = sloganInput,
      icon = selectedIcon,
      colorScheme = selectedColorScheme,
      fontStyle = selectedFontStyle,
      fontSize = fontSize,
      layout = selectedLayout
    )
    val updatedSet = currentSet.toMutableSet().apply {
      add(newDesign.toPersistableString())
    }
    prefs.edit().putStringSet("saved_designs_set", updatedSet).apply()
    savedDesigns = updatedSet.mapNotNull { SavedDesign.fromPersistableString(it) }.sortedByDescending { it.id }
    android.widget.Toast.makeText(context, "Design saved to SharedPreferences Library!", android.widget.Toast.LENGTH_SHORT).show()
  }

  val loadDesign = { design: SavedDesign ->
    brandNameInput = design.title
    selectedType = design.type
    selectedStyle = design.style
    sloganInput = design.slogan
    selectedIcon = design.icon
    selectedColorScheme = design.colorScheme
    selectedFontStyle = design.fontStyle
    fontSize = design.fontSize
    selectedLayout = design.layout
    android.widget.Toast.makeText(context, "Loaded saved design: '${design.title}'", android.widget.Toast.LENGTH_SHORT).show()
  }

  val deleteDesign = { design: SavedDesign ->
    val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
    val currentSet = prefs.getStringSet("saved_designs_set", emptySet()) ?: emptySet()
    val updatedSet = currentSet.toMutableSet().apply {
      val found = firstOrNull { SavedDesign.fromPersistableString(it)?.id == design.id }
      if (found != null) {
        remove(found)
      }
    }
    prefs.edit().putStringSet("saved_designs_set", updatedSet).apply()
    savedDesigns = updatedSet.mapNotNull { SavedDesign.fromPersistableString(it) }.sortedByDescending { it.id }
    android.widget.Toast.makeText(context, "Deleted design from Library.", android.widget.Toast.LENGTH_SHORT).show()
  }

  val saveAiDesignToLibrary = { prompt: String, title: String, imageUrl: String, cloudUrl: String? ->
    val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
    val currentSet = prefs.getStringSet("saved_ai_designs_set", emptySet()) ?: emptySet()
    val newDesign = SavedAiDesign(
      id = System.currentTimeMillis().toString(),
      prompt = prompt,
      title = title,
      imageUrl = imageUrl,
      cloudUrl = cloudUrl,
      timestamp = System.currentTimeMillis()
    )
    val updatedSet = currentSet.toMutableSet().apply {
      add(newDesign.toPersistableString())
    }
    prefs.edit().putStringSet("saved_ai_designs_set", updatedSet).apply()
    savedAiDesigns = updatedSet.mapNotNull { SavedAiDesign.fromPersistableString(it) }.sortedByDescending { it.id }
    android.widget.Toast.makeText(context, if (cloudUrl != null) "Design saved & uploaded to Firebase Storage!" else "Design saved to local library!", android.widget.Toast.LENGTH_LONG).show()
  }

  val deleteAiDesign = { design: SavedAiDesign ->
    val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
    val currentSet = prefs.getStringSet("saved_ai_designs_set", emptySet()) ?: emptySet()
    val updatedSet = currentSet.filter { 
      val parsed = SavedAiDesign.fromPersistableString(it)
      parsed?.id != design.id
    }.toSet()
    prefs.edit().putStringSet("saved_ai_designs_set", updatedSet).apply()
    savedAiDesigns = updatedSet.mapNotNull { SavedAiDesign.fromPersistableString(it) }.sortedByDescending { it.id }
    android.widget.Toast.makeText(context, "Removed design from library", android.widget.Toast.LENGTH_SHORT).show()
  }

  val uploadExistingToCloud: (SavedAiDesign, (Boolean) -> Unit) -> Unit = { design, onProgressChange ->
    uploadImageToFirebaseStorage(
      context = context,
      imageUrl = design.imageUrl,
      title = design.title,
      onProgressChange = onProgressChange,
      onComplete = { cloudUrl, errorMsg ->
        if (cloudUrl != null) {
          val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
          val currentSet = prefs.getStringSet("saved_ai_designs_set", emptySet()) ?: emptySet()
          val updatedSet = currentSet.map { str ->
            val parsed = SavedAiDesign.fromPersistableString(str)
            if (parsed?.id == design.id) {
              parsed.copy(cloudUrl = cloudUrl).toPersistableString()
            } else {
              str
            }
          }.toSet()
          prefs.edit().putStringSet("saved_ai_designs_set", updatedSet).apply()
          savedAiDesigns = updatedSet.mapNotNull { SavedAiDesign.fromPersistableString(it) }.sortedByDescending { it.id }
          android.widget.Toast.makeText(context, "Successfully uploaded design to Firebase Storage!", android.widget.Toast.LENGTH_LONG).show()
        } else {
          android.widget.Toast.makeText(context, "Firebase upload failed: $errorMsg", android.widget.Toast.LENGTH_LONG).show()
        }
      }
    )
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = MaterialTheme.colorScheme.background
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(
              Color(0xFF0F1115), // From HTML: bg-[#0F1115]
              Color(0xFF13151B), // Dark slate
              Color(0xFF08090C)  // Pure deep black-charcoal base
            )
          )
        )
        .padding(
          top = innerPadding.calculateTopPadding(),
          bottom = innerPadding.calculateBottomPadding()
        )
    ) {
      // Main Scrollable Content
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(scrollState)
          .align(Alignment.TopCenter),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        
        // 1. Navigation Bar
        NavigationBar(
          onBrandClick = {
            coroutineScope.launch { scrollState.animateScrollTo(0) }
          },
          onNavigate = { destination ->
            coroutineScope.launch {
              val targetOffset = when (destination) {
                "features" -> featuresOffset.toInt()
                "gallery" -> galleryOffset.toInt()
                "pricing" -> pricingOffset.toInt()
                "faq" -> faqOffset.toInt()
                else -> 0
              }
              scrollState.animateScrollTo(targetOffset - 60) // offset for floating nav spacing
            }
          },
          onStartTrialClick = { showTrialDialog = true },
          isLoggedIn = isLoggedIn,
          loggedInEmail = loggedInEmail,
          onLoginClick = { showLoginDialog = true },
          onLogoutClick = {
            val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
            prefs.edit()
              .putBoolean("is_logged_in", false)
              .putString("logged_in_email", "")
              .apply()
            isLoggedIn = false
            loggedInEmail = ""
            android.widget.Toast.makeText(context, "Successfully logged out.", android.widget.Toast.LENGTH_SHORT).show()
          }
        )

        // 2. Hero Section & Interactive Customizer
        HeroSection(
          brandName = brandNameInput,
          onBrandNameChange = { brandNameInput = it },
          slogan = sloganInput,
          onSloganChange = { sloganInput = it },
          selectedType = selectedType,
          onTypeSelect = { selectedType = it },
          selectedStyle = selectedStyle,
          onStyleSelect = { selectedStyle = it },
          selectedIcon = selectedIcon,
          onIconSelect = { selectedIcon = it },
          selectedColorScheme = selectedColorScheme,
          onColorSchemeSelect = { selectedColorScheme = it },
          selectedFontStyle = selectedFontStyle,
          onFontStyleSelect = { selectedFontStyle = it },
          selectedLayout = selectedLayout,
          onLayoutSelect = { selectedLayout = it },
          fontSize = fontSize,
          onFontSizeChange = { fontSize = it },
          onPrimaryCTA = { showTrialDialog = true },
          onSecondaryCTA = {
            coroutineScope.launch {
              scrollState.animateScrollTo(pricingOffset.toInt() - 60)
            }
          },
          onSimulateDownload = { title, type ->
            lastDownloadedTitle = title
            lastDownloadedType = type
            showDownloadSuccessDialog = true
          },
          onSaveToGallery = saveDesignToLibrary
        )

        // Divider styling
        GlowDivider()

        // Local creation library section
        SavedCreationsSection(
          savedDesigns = savedDesigns,
          onLoad = loadDesign,
          onDelete = deleteDesign
        )

        // Divider styling
        GlowDivider()

        // AI Design Needs Prompt Generator Section
        AiDesignNeedsSection(
          savedAiDesigns = savedAiDesigns,
          onSaveAiDesign = saveAiDesignToLibrary,
          onDeleteAiDesign = deleteAiDesign,
          onUploadToCloud = uploadExistingToCloud
        )

        // Divider styling
        GlowDivider()

        // 3. How It Works / Live Preview Steps
        HowItWorksSection()

        // Divider styling
        GlowDivider(modifier = Modifier.onGloballyPositioned {
          // Store features position
          featuresOffset = it.positionInParent().y
        })

        // 4. "Why Us" Feature Grid
        WhyUsSection()

        GlowDivider(modifier = Modifier.onGloballyPositioned {
          // Store gallery position
          galleryOffset = it.positionInParent().y
        })

        // 5. Social Proof / Template Gallery
        SocialProofGallerySection(
          onTemplateClick = { name, type, style ->
            brandNameInput = name
            selectedType = type
            selectedStyle = style
            // Scroll back up to Hero customizer with pleasant animations
            coroutineScope.launch {
              scrollState.animateScrollTo(0)
            }
          }
        )

        GlowDivider(modifier = Modifier.onGloballyPositioned {
          // Store pricing position
          pricingOffset = it.positionInParent().y
        })

        // 6. Interactive Pricing Section
        PricingSection(
          onSelectTier = { tierName, price ->
            if (tierName == "Free Trial") {
              showTrialDialog = true
            } else {
              selectedPaymentTier = tierName
              selectedPaymentPrice = price
              showUpiPaymentDialog = true
            }
          }
        )

        GlowDivider()

        // Highlight Free Trial Offer (No Credit Card Required) Section
        FreeTrialHighlightSection(
          onStartTrialClick = { showTrialDialog = true }
        )

        GlowDivider(modifier = Modifier.onGloballyPositioned {
          // Store faq position
          faqOffset = it.positionInParent().y
        })

        // 7. FAQ Section
        FaqSection()

        // Footer Divider
        Spacer(modifier = Modifier.height(64.dp))
        Divider(color = Color(0xFF334155), thickness = 1.dp, modifier = Modifier.fillMaxWidth(0.9f))

        // 8. Footer Section
        FooterSection(
          onNavigate = { destination ->
            coroutineScope.launch {
              val targetOffset = when (destination) {
                "features" -> featuresOffset.toInt()
                "gallery" -> galleryOffset.toInt()
                "pricing" -> pricingOffset.toInt()
                "faq" -> faqOffset.toInt()
                else -> 0
              }
              scrollState.animateScrollTo(targetOffset - 60)
            }
          },
          onTryFree = { showTrialDialog = true }
        )
      }

      // 9. Floating Top Navigation Bar Sticky Background
      // Standard static/floating top navbar is integrated on top scroll coordinate. 
    }
  }

  // Conversion Modals
  if (showUpiPaymentDialog) {
    UpiPaymentDialog(
      planName = selectedPaymentTier,
      price = selectedPaymentPrice,
      onDismiss = { showUpiPaymentDialog = false },
      onPaymentSuccess = { txnId ->
        showUpiPaymentDialog = false
        val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
        prefs.edit().putBoolean("is_premium_user", true).apply()
        isPremiumUser = true
        android.widget.Toast.makeText(context, "Payment Successful! Transaction ID: $txnId. Premium features unlocked!", android.widget.Toast.LENGTH_LONG).show()
      }
    )
  }

  if (showTrialDialog) {
    FreeTrialFormDialog(
      onDismiss = { showTrialDialog = false },
      onSubmit = { email, bizName ->
        showTrialDialog = false
        // Trigger simulated celebration
        lastDownloadedTitle = if (bizName.isNotBlank()) bizName else brandNameInput
        lastDownloadedType = "Creative Kit"
        showDownloadSuccessDialog = true
      }
    )
  }

  if (showDownloadSuccessDialog) {
    SimulatedCelebrationDialog(
      title = lastDownloadedTitle,
      type = lastDownloadedType,
      onDismiss = { showDownloadSuccessDialog = false }
    )
  }

  if (showLoginDialog) {
    LoginFormDialog(
      onDismiss = { showLoginDialog = false },
      onLoginSuccess = { email ->
        val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
        prefs.edit()
          .putBoolean("is_logged_in", true)
          .putString("logged_in_email", email)
          .apply()
        isLoggedIn = true
        loggedInEmail = email
        showLoginDialog = false
        android.widget.Toast.makeText(context, "Welcome back, $email!", android.widget.Toast.LENGTH_LONG).show()
      }
    )
  }
}

// Navigation Bar Composable
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NavigationBar(
  onBrandClick: () -> Unit,
  onNavigate: (String) -> Unit,
  onStartTrialClick: () -> Unit,
  isLoggedIn: Boolean = false,
  loggedInEmail: String = "",
  onLoginClick: () -> Unit = {},
  onLogoutClick: () -> Unit = {}
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("navigation_bar"),
    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
    shape = RoundedCornerShape(24.dp),
    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Left: Brand Logo + BrandCraft
      Row(
        modifier = Modifier
          .clickable(onClick = onBrandClick)
          .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Geometric Logo Drawing
        Box(
          modifier = Modifier
            .size(36.dp)
            .background(
              brush = Brush.linearGradient(
                colors = listOf(Color(0xFF7C3AED), Color(0xFF60A5FA))
              ),
              shape = RoundedCornerShape(10.dp)
            )
            .padding(2.dp),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Palette,
            contentDescription = "BrandCraft Icon",
            tint = Color(0xFF0F1115),
            modifier = Modifier.size(20.dp)
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "BrandCraft",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Black,
            letterSpacing = 0.5.sp,
            color = Color.White
          )
        )
      }

      // Center Links - Hidden on very compact widths, wrap beautifully using FlowRow otherwise
      FlowRow(
        modifier = Modifier
          .weight(1f, fill = false)
          .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
      ) {
        val navItems = listOf(
          "Features" to "features",
          "Gallery" to "gallery",
          "Pricing" to "pricing",
          "FAQ" to "faq"
        )
        navItems.forEach { (label, dest) ->
          Text(
            text = label,
            modifier = Modifier
              .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onNavigate(dest) }
              )
              .padding(horizontal = 10.dp, vertical = 6.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
              fontWeight = FontWeight.SemiBold,
              color = Color(0xFF94A3B8)
            )
          )
        }
      }

      // Right action buttons (Sign In / Start Free / User Profile Avatar)
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        if (isLoggedIn) {
          val userLetter = if (loggedInEmail.isNotEmpty()) loggedInEmail.first().uppercase() else "U"
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
              .background(Color(0xFF1E293B), RoundedCornerShape(16.dp))
              .border(BorderStroke(1.dp, Color(0xFF334155)), RoundedCornerShape(16.dp))
              .padding(horizontal = 8.dp, vertical = 4.dp)
              .testTag("user_profile_badge")
          ) {
            Box(
              modifier = Modifier
                .size(24.dp)
                .background(
                  brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF38BDF8), Color(0xFF818CF8))
                  ),
                  shape = CircleShape
                ),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = userLetter,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF0F172A),
                  fontSize = 11.sp
                )
              )
            }

            Text(
              text = loggedInEmail.substringBefore("@"),
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              ),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis,
              modifier = Modifier.widthIn(max = 80.dp)
            )

            IconButton(
              onClick = onLogoutClick,
              modifier = Modifier
                .size(20.dp)
                .testTag("logout_btn")
            ) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Log Out",
                tint = Color(0xFFEF4444),
                modifier = Modifier.size(12.dp)
              )
            }
          }
        } else {
          OutlinedButton(
            onClick = onLoginClick,
            colors = ButtonDefaults.outlinedButtonColors(
              containerColor = Color.Transparent,
              contentColor = Color(0xFF38BDF8)
            ),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f)),
            modifier = Modifier
              .testTag("login_nav_btn")
              .height(38.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
          ) {
            Text(
              text = "Sign In",
              style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF38BDF8),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
              )
            )
          }

          // Right: High-Contrast "Start Free Trial" Button
          OutlinedButton(
            onClick = onStartTrialClick,
            colors = ButtonDefaults.outlinedButtonColors(
              containerColor = Color(0xFF7C3AED).copy(alpha = 0.2f),
              contentColor = Color(0xFFA78BFA)
            ),
            shape = RoundedCornerShape(24.dp),
            border = BorderStroke(1.dp, Color(0xFF7C3AED).copy(alpha = 0.3f)),
            modifier = Modifier
              .testTag("start_free_trial_btn")
              .height(38.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 0.dp)
          ) {
            Text(
              text = "Start Free",
              style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFFA78BFA),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
              )
            )
          }
        }
      }
    }
  }
}

// A beautiful glowing divider line
@Composable
fun GlowDivider(modifier: Modifier = Modifier) {
  Spacer(
    modifier = modifier
      .fillMaxWidth()
      .height(1.dp)
      .background(
        Brush.horizontalGradient(
          colors = listOf(
            Color.Transparent,
            Color(0xFF38BDF8).copy(alpha = 0.4f),
            Color(0xFFC084FC).copy(alpha = 0.4f),
            Color.Transparent
          )
        )
      )
  )
}

// Hero Section & Customizer
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
  brandName: String,
  onBrandNameChange: (String) -> Unit,
  slogan: String,
  onSloganChange: (String) -> Unit,
  selectedType: DesignType,
  onTypeSelect: (DesignType) -> Unit,
  selectedStyle: DesignStyle,
  onStyleSelect: (DesignStyle) -> Unit,
  selectedIcon: BrandIcon,
  onIconSelect: (BrandIcon) -> Unit,
  selectedColorScheme: BrandColorScheme,
  onColorSchemeSelect: (BrandColorScheme) -> Unit,
  selectedFontStyle: BrandFontStyle,
  onFontStyleSelect: (BrandFontStyle) -> Unit,
  selectedLayout: BrandLayout,
  onLayoutSelect: (BrandLayout) -> Unit,
  fontSize: Float,
  onFontSizeChange: (Float) -> Unit,
  onPrimaryCTA: () -> Unit,
  onSecondaryCTA: () -> Unit,
  onSimulateDownload: (String, String) -> Unit,
  onSaveToGallery: () -> Unit
) {
  val focusManager = LocalFocusManager.current

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 40.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Glowing Badge
    Box(
      modifier = Modifier
        .background(Color(0xFF1E1B4B), shape = RoundedCornerShape(20.dp))
        .border(1.dp, Color(0xFF818CF8).copy(alpha = 0.5f), shape = RoundedCornerShape(20.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Star,
          contentDescription = null,
          tint = Color(0xFFC084FC),
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "UNLIMITED FREE TRIAL — INSTANT GENERATOR",
          style = MaterialTheme.typography.labelSmall.copy(
            color = Color(0xFFC084FC),
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
          )
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    val titleGradient = remember {
      Brush.linearGradient(
        colors = listOf(Color(0xFFA78BFA), Color(0xFF60A5FA)) // Violet-400 to Blue-400
      )
    }

    Text(
      text = buildAnnotatedString {
        append("Logos & Posters.\n")
        withStyle(SpanStyle(brush = titleGradient, fontWeight = FontWeight.ExtraBold)) {
          append("Done in 2 Mins.")
        }
      },
      style = MaterialTheme.typography.displayLarge.copy(
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 48.sp
      ),
      textAlign = TextAlign.Center,
      modifier = Modifier.widthIn(max = 680.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Subtitle
    Text(
      text = "Create stunning visuals for your brand or event. Start with a risk-free Free Trial—no credit card required. Packages starting at ultra-low prices.",
      style = MaterialTheme.typography.bodyLarge,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier.widthIn(max = 620.dp),
      lineHeight = 24.sp
    )

    Spacer(modifier = Modifier.height(28.dp))

    // CTA Button Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Button(
        onClick = onPrimaryCTA,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC084FC)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
          .height(52.dp)
          .testTag("create_for_free_btn"),
        contentPadding = PaddingValues(horizontal = 24.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Create for Free",
            style = MaterialTheme.typography.labelLarge.copy(
              color = Color(0xFF0F172A),
              fontWeight = FontWeight.Black
            )
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color(0xFF0F172A),
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.width(16.dp))

      OutlinedButton(
        onClick = onSecondaryCTA,
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFF334155)),
        modifier = Modifier.height(52.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
      ) {
        Text(
          text = "View Pricing",
          style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
      }
    }

    Spacer(modifier = Modifier.height(48.dp))

    // INTERACTIVE BRANDCRAFT EDITOR (The Core visual engine)
    Text(
      text = "Live Interactive Preview Tool",
      style = MaterialTheme.typography.headlineLarge.copy(
        fontWeight = FontWeight.Black,
        color = Color.White
      ),
      textAlign = TextAlign.Center
    )
    Text(
      text = "Type your brand name below and customize your assets in real-time!",
      style = MaterialTheme.typography.bodyMedium,
      color = Color(0xFF94A3B8),
      modifier = Modifier.padding(top = 4.dp, bottom = 20.dp),
      textAlign = TextAlign.Center
    )

    // Layout accommodating sidebar controls and live output pane
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 900.dp)
        .testTag("interactive_editor_card")
        .drawBehind {
          // Glow spot underneath the frosted glass card (simulates the inset-2 blur glow)
          drawCircle(
            brush = Brush.radialGradient(
              colors = listOf(Color(0xFF7C3AED).copy(alpha = 0.15f), Color.Transparent),
              center = Offset(size.width / 2, size.height / 2),
              radius = size.width.coerceAtLeast(size.height) * 0.75f
            )
          )
        },
      colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
      shape = RoundedCornerShape(24.dp),
      border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f)),
      elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
      ) {
        // Responsive Row/Column
        BoxWithConstraints {
          val isWide = maxWidth > 600.dp
          if (isWide) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              // Left: Controls Panel
              Column(
                modifier = Modifier
                  .weight(1f)
                  .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
              ) {
                EditorControlsPanel(
                  brandName = brandName,
                  onBrandNameChange = onBrandNameChange,
                  slogan = slogan,
                  onSloganChange = onSloganChange,
                  selectedType = selectedType,
                  onTypeSelect = onTypeSelect,
                  selectedStyle = selectedStyle,
                  onStyleSelect = onStyleSelect,
                  selectedIcon = selectedIcon,
                  onIconSelect = onIconSelect,
                  selectedColorScheme = selectedColorScheme,
                  onColorSchemeSelect = onColorSchemeSelect,
                  selectedFontStyle = selectedFontStyle,
                  onFontStyleSelect = onFontStyleSelect,
                  selectedLayout = selectedLayout,
                  onLayoutSelect = onLayoutSelect,
                  fontSize = fontSize,
                  onFontSizeChange = onFontSizeChange,
                  focusManager = focusManager
                )
              }

              // Right: Canvas Output Pane
              Column(
                modifier = Modifier
                  .weight(1.2f)
                  .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                LiveRenderOutputPane(
                  title = brandName,
                  type = selectedType,
                  style = selectedStyle,
                  slogan = slogan,
                  icon = selectedIcon,
                  colorScheme = selectedColorScheme,
                  fontStyle = selectedFontStyle,
                  fontSize = fontSize,
                  layout = selectedLayout,
                  onSimulateDownload = onSimulateDownload,
                  onSaveToGallery = onSaveToGallery
                )
              }
            }
          } else {
            Column(
              modifier = Modifier.fillMaxWidth(),
              verticalArrangement = Arrangement.spacedBy(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              // Top: Controls
              EditorControlsPanel(
                brandName = brandName,
                onBrandNameChange = onBrandNameChange,
                slogan = slogan,
                onSloganChange = onSloganChange,
                selectedType = selectedType,
                onTypeSelect = onTypeSelect,
                selectedStyle = selectedStyle,
                onStyleSelect = onStyleSelect,
                selectedIcon = selectedIcon,
                onIconSelect = onIconSelect,
                selectedColorScheme = selectedColorScheme,
                onColorSchemeSelect = onColorSchemeSelect,
                selectedFontStyle = selectedFontStyle,
                onFontStyleSelect = onFontStyleSelect,
                selectedLayout = selectedLayout,
                onLayoutSelect = onLayoutSelect,
                fontSize = fontSize,
                onFontSizeChange = onFontSizeChange,
                focusManager = focusManager
              )

              // Bottom: Render Output
              LiveRenderOutputPane(
                title = brandName,
                type = selectedType,
                style = selectedStyle,
                slogan = slogan,
                icon = selectedIcon,
                colorScheme = selectedColorScheme,
                fontStyle = selectedFontStyle,
                fontSize = fontSize,
                layout = selectedLayout,
                onSimulateDownload = onSimulateDownload,
                onSaveToGallery = onSaveToGallery
              )
            }
          }
        }
      }
    }
  }
}

// Editor Controls Panel Component
@Composable
fun EditorControlsPanel(
  brandName: String,
  onBrandNameChange: (String) -> Unit,
  slogan: String,
  onSloganChange: (String) -> Unit,
  selectedType: DesignType,
  onTypeSelect: (DesignType) -> Unit,
  selectedStyle: DesignStyle,
  onStyleSelect: (DesignStyle) -> Unit,
  selectedIcon: BrandIcon,
  onIconSelect: (BrandIcon) -> Unit,
  selectedColorScheme: BrandColorScheme,
  onColorSchemeSelect: (BrandColorScheme) -> Unit,
  selectedFontStyle: BrandFontStyle,
  onFontStyleSelect: (BrandFontStyle) -> Unit,
  selectedLayout: BrandLayout,
  onLayoutSelect: (BrandLayout) -> Unit,
  fontSize: Float,
  onFontSizeChange: (Float) -> Unit,
  focusManager: FocusManager
) {
  // Brand name input field
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.TextFields,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Brand / Event Name",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
      value = brandName,
      onValueChange = onBrandNameChange,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("brand_name_textfield"),
      shape = RoundedCornerShape(12.dp),
      placeholder = { Text("Enter brand name...", color = Color(0xFF64748B)) },
      singleLine = true,
      colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedBorderColor = Color(0xFF38BDF8),
        unfocusedBorderColor = Color(0xFF334155),
        focusedContainerColor = Color(0xFF0F172A),
        unfocusedContainerColor = Color(0xFF0F172A)
      ),
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Slogan / Tagline input field
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.TextFields,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Slogan / Tagline Text",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    OutlinedTextField(
      value = slogan,
      onValueChange = onSloganChange,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("slogan_name_textfield"),
      shape = RoundedCornerShape(12.dp),
      placeholder = { Text("Enter tagline...", color = Color(0xFF64748B)) },
      singleLine = true,
      colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedBorderColor = Color(0xFF38BDF8),
        unfocusedBorderColor = Color(0xFF334155),
        focusedContainerColor = Color(0xFF0F172A),
        unfocusedContainerColor = Color(0xFF0F172A)
      ),
      keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
      keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Template Type selector (Poster vs Logo)
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.PhotoLibrary,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Design Category",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      DesignType.values().forEach { type ->
        val isActive = selectedType == type
        val bgState by animateColorAsState(targetValue = if (isActive) Color(0xFF38BDF8) else Color(0xFF0F172A))
        val textState by animateColorAsState(targetValue = if (isActive) Color(0xFF0F172A) else Color(0xFF94A3B8))

        Box(
          modifier = Modifier
            .weight(1f)
            .height(44.dp)
            .background(bgState, shape = RoundedCornerShape(12.dp))
            .border(
              1.dp,
              if (isActive) Color(0xFF38BDF8) else Color(0xFF334155),
              shape = RoundedCornerShape(12.dp)
            )
            .clickable { onTypeSelect(type) }
            .testTag("type_tab_${type.name.lowercase()}"),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = type.label,
            style = MaterialTheme.typography.bodyMedium.copy(
              fontWeight = FontWeight.Bold,
              color = textState
            )
          )
        }
      }
    }
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Layout selection
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.Build,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Component Layout Pattern",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      BrandLayout.values().forEach { layoutOption ->
        val isActive = selectedLayout == layoutOption
        val bgState by animateColorAsState(targetValue = if (isActive) Color(0xFF38BDF8) else Color(0xFF0F172A))
        val textState by animateColorAsState(targetValue = if (isActive) Color(0xFF0F172A) else Color(0xFF94A3B8))

        Box(
          modifier = Modifier
            .weight(1f)
            .height(38.dp)
            .background(bgState, shape = RoundedCornerShape(10.dp))
            .border(
              1.dp,
              if (isActive) Color(0xFF38BDF8) else Color(0xFF334155),
              shape = RoundedCornerShape(10.dp)
            )
            .clickable { onLayoutSelect(layoutOption) }
            .testTag("layout_tab_${layoutOption.name.lowercase()}"),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = layoutOption.label,
            style = MaterialTheme.typography.bodySmall.copy(
              fontWeight = FontWeight.Bold,
              color = textState
            )
          )
        }
      }
    }
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Icon selector scroll
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.ColorLens,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Graphic Logo Icon Symbol",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      BrandIcon.values().forEach { iconOption ->
        val isActive = selectedIcon == iconOption
        Box(
          modifier = Modifier
            .size(54.dp)
            .background(
              if (isActive) Color(0x3338BDF8) else Color(0xFF0F172A),
              shape = RoundedCornerShape(12.dp)
            )
            .border(
              1.dp,
              if (isActive) Color(0xFF38BDF8) else Color(0xFF334155),
              shape = RoundedCornerShape(12.dp)
            )
            .clickable { onIconSelect(iconOption) }
            .testTag("icon_picker_${iconOption.name.lowercase()}"),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = iconOption.icon,
            contentDescription = iconOption.label,
            tint = if (isActive) Color(0xFF38BDF8) else Color.White,
            modifier = Modifier.size(24.dp)
          )
        }
      }
    }
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Color Theme preset selector scroll
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.Palette,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Color Palette Preset",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      BrandColorScheme.values().forEach { scheme ->
        val isActive = selectedColorScheme == scheme
        Box(
          modifier = Modifier
            .background(
              if (isActive) Color(0x3338BDF8) else Color(0xFF0F172A),
              shape = RoundedCornerShape(12.dp)
            )
            .border(
              1.dp,
              if (isActive) Color(0xFF38BDF8) else Color(0xFF334155),
              shape = RoundedCornerShape(12.dp)
            )
            .clickable { onColorSchemeSelect(scheme) }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .testTag("color_preset_${scheme.name.lowercase()}")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(14.dp)
                .background(scheme.primary, shape = RoundedCornerShape(3.dp))
            )
            Box(
              modifier = Modifier
                .size(14.dp)
                .background(scheme.secondary, shape = RoundedCornerShape(3.dp))
            )
            Text(
              text = scheme.label,
              style = MaterialTheme.typography.bodySmall,
              color = Color.White,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Typography choice scroll
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.TextFields,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Brand Typography Choice",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState()),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      BrandFontStyle.values().forEach { font ->
        val isActive = selectedFontStyle == font
        Box(
          modifier = Modifier
            .background(
              if (isActive) Color(0x3338BDF8) else Color(0xFF0F172A),
              shape = RoundedCornerShape(12.dp)
            )
            .border(
              1.dp,
              if (isActive) Color(0xFF38BDF8) else Color(0xFF334155),
              shape = RoundedCornerShape(12.dp)
            )
            .clickable { onFontStyleSelect(font) }
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .testTag("font_preset_${font.name.lowercase()}")
        ) {
          Text(
            text = font.label,
            style = MaterialTheme.typography.bodySmall.copy(
              fontFamily = font.fontFamily,
              color = Color.White,
              fontWeight = FontWeight.Bold
            )
          )
        }
      }
    }
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Font size adjuster Slider
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.TextFields,
          contentDescription = null,
          tint = Color(0xFF38BDF8),
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Text Font Size Ratio",
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }
      Text(
        text = "${fontSize.toInt()} sp",
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFF38BDF8),
        fontWeight = FontWeight.Bold
      )
    }
    Slider(
      value = fontSize,
      onValueChange = onFontSizeChange,
      valueRange = 12f..36f,
      colors = SliderDefaults.colors(
        thumbColor = Color(0xFF38BDF8),
        activeTrackColor = Color(0xFF38BDF8),
        inactiveTrackColor = Color(0xFF334155)
      ),
      modifier = Modifier.testTag("font_size_slider")
    )
  }

  Spacer(modifier = Modifier.height(12.dp))

  // Aesthetics Style Select (presets)
  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = Icons.Default.FormatPaint,
        contentDescription = null,
        tint = Color(0xFF38BDF8),
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Geometric Frame & Mood",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = Color.White
      )
    }
    Spacer(modifier = Modifier.height(6.dp))
    Column(
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      DesignStyle.values().forEach { style ->
        val isActive = selectedStyle == style
        val bgState by animateColorAsState(targetValue = if (isActive) Color(0x33C084FC) else Color(0xFF0F172A))
        val borderState by animateColorAsState(targetValue = if (isActive) Color(0xFFC084FC) else Color(0xFF334155))

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(bgState, shape = RoundedCornerShape(12.dp))
            .border(1.dp, borderState, shape = RoundedCornerShape(12.dp))
            .clickable { onStyleSelect(style) }
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .testTag("style_row_${style.name.lowercase()}"),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = style.label,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            )
            Text(
              text = style.desc,
              style = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFF94A3B8)
              ),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }
          if (isActive) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = "Selected",
              tint = Color(0xFFC084FC),
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }
    }
  }
}

// Live Render Output Canvas Panel
@Composable
fun LiveRenderOutputPane(
  title: String,
  type: DesignType,
  style: DesignStyle,
  slogan: String,
  icon: BrandIcon,
  colorScheme: BrandColorScheme,
  fontStyle: BrandFontStyle,
  fontSize: Float,
  layout: BrandLayout,
  onSimulateDownload: (String, String) -> Unit,
  onSaveToGallery: () -> Unit
) {
  val context = LocalContext.current
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxWidth()
  ) {
    // Beautiful Framed Box for Canvas
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 260.dp, max = 340.dp)
        .testTag("canvas_preview_frame"),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
      shape = RoundedCornerShape(16.dp),
      border = BorderStroke(1.dp, Color(0xFF334155))
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        contentAlignment = Alignment.Center
      ) {
        // Draw the dynamic poster/logo according to selected specs
        AnimatedContent(
          targetState = Pair(type, style),
          transitionSpec = {
            fadeIn(animationSpec = tween(220)) togetherWith fadeOut(animationSpec = tween(220))
          },
          label = "render_animation"
        ) { (currentType, currentStyle) ->
          if (currentType == DesignType.POSTER) {
            PosterCanvas(
              title = title,
              style = currentStyle,
              slogan = slogan,
              icon = icon,
              colorScheme = colorScheme,
              fontStyle = fontStyle,
              fontSize = fontSize,
              layout = layout
            )
          } else {
            LogoCanvas(
              title = title,
              style = currentStyle,
              slogan = slogan,
              icon = icon,
              colorScheme = colorScheme,
              fontStyle = fontStyle,
              fontSize = fontSize,
              layout = layout
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Free Draft download simulator
      Button(
        onClick = {
          onSimulateDownload(title, type.label)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
          .weight(1.1f)
          .height(44.dp)
          .testTag("download_instant_draft_btn")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.CloudDownload,
            contentDescription = null,
            tint = Color(0xFF0F172A),
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "Simulate",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Color(0xFF0F172A),
              fontWeight = FontWeight.Bold
            )
          )
        }
      }

      // Real PNG Direct Download button
      Button(
        onClick = {
          exportBitmapAndSaveOrShare(
            context = context,
            title = title,
            type = type,
            style = style,
            slogan = slogan,
            icon = icon,
            colorScheme = colorScheme,
            fontStyle = fontStyle,
            fontSize = fontSize,
            layout = layout,
            shareAfterSave = false
          )
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF43F5E)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
          .weight(1.1f)
          .height(44.dp)
          .testTag("download_real_png_btn")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.Download,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "Download",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Color.White,
              fontWeight = FontWeight.Bold
            )
          )
        }
      }

      // Real PNG export button
      Button(
        onClick = {
          exportBitmapAndSaveOrShare(
            context = context,
            title = title,
            type = type,
            style = style,
            slogan = slogan,
            icon = icon,
            colorScheme = colorScheme,
            fontStyle = fontStyle,
            fontSize = fontSize,
            layout = layout,
            shareAfterSave = true
          )
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
          .weight(1.1f)
          .height(44.dp)
          .testTag("export_real_png_btn")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "Export",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Color.White,
              fontWeight = FontWeight.Bold
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Save design to creation library
    OutlinedButton(
      onClick = onSaveToGallery,
      border = BorderStroke(1.dp, Color(0xFF334155)),
      shape = RoundedCornerShape(12.dp),
      colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF38BDF8)),
      modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .testTag("save_to_creations_btn")
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Icon(
          imageVector = Icons.Default.Bookmark,
          contentDescription = null,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Save Design to Library",
          style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
      }
    }
  }
}

// 2D Canvas rendering for Custom Logos
@Composable
fun LogoCanvas(
  title: String,
  style: DesignStyle,
  slogan: String = "CREATIVE LABS",
  icon: BrandIcon = BrandIcon.PALETTE,
  colorScheme: BrandColorScheme = BrandColorScheme.CYBER_GLOW,
  fontStyle: BrandFontStyle = BrandFontStyle.SANS,
  fontSize: Float = 24f,
  layout: BrandLayout = BrandLayout.ICON_TOP
) {
  val cleanTitle = if (title.isBlank()) "Acme" else title
  val primaryColor = colorScheme.primary
  val secondaryColor = colorScheme.secondary

  val iconContent = @Composable {
    if (layout != BrandLayout.TEXT_ONLY) {
      Box(
        modifier = Modifier
          .size(100.dp)
          .drawBehind {
            when (style) {
              DesignStyle.CYBERPUNK -> {
                val brush = Brush.linearGradient(
                  colors = listOf(primaryColor, secondaryColor),
                  start = Offset.Zero,
                  end = Offset(size.width, size.height)
                )
                drawCircle(
                  color = primaryColor.copy(alpha = 0.2f),
                  radius = size.minDimension / 2.1f,
                  style = Stroke(width = 2.dp.toPx(), pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 15f)))
                )
                drawCircle(
                  brush = brush,
                  radius = size.minDimension / 2.7f,
                  style = Stroke(width = 4.dp.toPx())
                )
                drawCircle(
                  color = primaryColor.copy(alpha = 0.15f),
                  radius = size.minDimension / 4f
                )
              }
              DesignStyle.MINIMALIST -> {
                val goldBrush = Brush.linearGradient(
                  colors = listOf(primaryColor, secondaryColor),
                  start = Offset.Zero,
                  end = Offset(size.width, size.height)
                )
                val path = Path().apply {
                  moveTo(size.width / 2, 10f)
                  lineTo(size.width - 10f, size.height / 2)
                  lineTo(size.width / 2, size.height - 10f)
                  lineTo(10f, size.height / 2)
                  close()
                }
                drawPath(
                  path = path,
                  brush = goldBrush,
                  style = Stroke(width = 2.dp.toPx())
                )
                drawRect(
                  brush = goldBrush,
                  topLeft = Offset(size.width / 2 - 12f, size.height / 2 - 12f),
                  size = Size(24f, 24f)
                )
              }
              DesignStyle.RETRO -> {
                val warmColors = listOf(primaryColor, secondaryColor, primaryColor.copy(alpha = 0.5f))
                warmColors.forEachIndexed { idx, color ->
                  drawCircle(
                    color = color,
                    radius = (size.minDimension / 2.2f) - (idx * 16f)
                  )
                }
              }
              DesignStyle.SWISS -> {
                drawRect(
                  color = primaryColor,
                  topLeft = Offset(10f, 10f),
                  size = Size(size.width - 20f, size.height - 20f),
                  style = Stroke(width = 6.dp.toPx())
                )
                drawRect(
                  color = secondaryColor,
                  topLeft = Offset(size.width / 2 - 8f, size.height / 2 - 20f),
                  size = Size(16f, 40f)
                )
                drawRect(
                  color = secondaryColor,
                  topLeft = Offset(size.width / 2 - 20f, size.height / 2 - 8f),
                  size = Size(40f, 16f)
                )
              }
            }
          },
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon.icon,
          contentDescription = null,
          tint = if (style == DesignStyle.RETRO) Color.White else primaryColor,
          modifier = Modifier.size(32.dp)
        )
      }
    }
  }

  val textContent = @Composable {
    Column(horizontalAlignment = if (layout == BrandLayout.ICON_LEFT) Alignment.Start else Alignment.CenterHorizontally) {
      Text(
        text = cleanTitle.uppercase(),
        style = androidx.compose.ui.text.TextStyle(
          fontFamily = fontStyle.fontFamily,
          fontWeight = FontWeight.Bold,
          color = primaryColor,
          fontSize = fontSize.sp,
          letterSpacing = if (fontStyle == BrandFontStyle.SERIF) 4.sp else 2.sp
        ),
        textAlign = if (layout == BrandLayout.ICON_LEFT) TextAlign.Start else TextAlign.Center
      )
      Text(
        text = slogan.uppercase(),
        style = androidx.compose.ui.text.TextStyle(
          fontFamily = fontStyle.fontFamily,
          fontWeight = FontWeight.Medium,
          color = secondaryColor.copy(alpha = 0.8f),
          fontSize = (fontSize * 0.45f).coerceAtLeast(8f).sp,
          letterSpacing = 4.sp
        ),
        modifier = Modifier.padding(top = 4.dp),
        textAlign = if (layout == BrandLayout.ICON_LEFT) TextAlign.Start else TextAlign.Center
      )
    }
  }

  if (layout == BrandLayout.ICON_LEFT) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier.padding(16.dp)
    ) {
      iconContent()
      textContent()
    }
  } else {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxSize()
    ) {
      iconContent()
      if (layout != BrandLayout.TEXT_ONLY) {
        Spacer(modifier = Modifier.height(16.dp))
      }
      textContent()
    }
  }
}

// 2D Canvas rendering for Custom Posters
@Composable
fun PosterCanvas(
  title: String,
  style: DesignStyle,
  slogan: String = "LIVE CONCERT",
  icon: BrandIcon = BrandIcon.PALETTE,
  colorScheme: BrandColorScheme = BrandColorScheme.CYBER_GLOW,
  fontStyle: BrandFontStyle = BrandFontStyle.SANS,
  fontSize: Float = 16f,
  layout: BrandLayout = BrandLayout.ICON_TOP
) {
  val cleanTitle = if (title.isBlank()) "SENSATIONAL" else title
  val primaryColor = colorScheme.primary
  val secondaryColor = colorScheme.secondary

  Box(
    modifier = Modifier
      .width(180.dp)
      .height(260.dp)
      .background(
        when (style) {
          DesignStyle.CYBERPUNK -> Color(0xFF0F172A)
          DesignStyle.MINIMALIST -> Color(0xFF1C1917)
          DesignStyle.RETRO -> Color(0xFF451A03)
          DesignStyle.SWISS -> Color(0xFF0F172A)
        },
        shape = RoundedCornerShape(8.dp)
      )
      .border(
        width = if (style == DesignStyle.MINIMALIST) 2.dp else 1.dp,
        brush = Brush.linearGradient(listOf(primaryColor, secondaryColor)),
        shape = RoundedCornerShape(8.dp)
      )
      .padding(12.dp)
  ) {
    Canvas(modifier = Modifier.fillMaxSize()) {
      when (style) {
        DesignStyle.CYBERPUNK -> {
          val gridGap = 20f
          for (x in 0..size.width.toInt() step gridGap.toInt()) {
            drawLine(
              color = primaryColor.copy(alpha = 0.15f),
              start = Offset(x.toFloat(), 0f),
              end = Offset(x.toFloat(), size.height),
              strokeWidth = 1f
            )
          }
          for (y in 0..size.height.toInt() step gridGap.toInt()) {
            drawLine(
              color = primaryColor.copy(alpha = 0.15f),
              start = Offset(0f, y.toFloat()),
              end = Offset(size.width, y.toFloat()),
              strokeWidth = 1f
            )
          }
          drawCircle(
            brush = Brush.radialGradient(
              listOf(secondaryColor.copy(alpha = 0.2f), Color.Transparent),
              center = Offset(size.width / 2, size.height / 1.8f),
              radius = size.width / 1.5f
            )
          )
        }
        DesignStyle.MINIMALIST -> {
          drawLine(
            color = primaryColor.copy(alpha = 0.3f),
            start = Offset(20f, 0f),
            end = Offset(20f, size.height),
            strokeWidth = 1.5f
          )
          drawLine(
            color = primaryColor.copy(alpha = 0.3f),
            start = Offset(0f, size.height - 60f),
            end = Offset(size.width, size.height - 60f),
            strokeWidth = 1.5f
          )
        }
        DesignStyle.RETRO -> {
          val colors = listOf(primaryColor, secondaryColor, primaryColor.copy(alpha = 0.5f))
          colors.forEachIndexed { idx, color ->
            drawRect(
              color = color.copy(alpha = 0.15f),
              topLeft = Offset(idx * 30f + 10f, 0f),
              size = Size(20f, size.height)
            )
          }
        }
        DesignStyle.SWISS -> {
          drawArc(
            color = primaryColor.copy(alpha = 0.4f),
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(size.width - 100f, size.height / 2),
            size = Size(140f, 140f)
          )
        }
      }
    }

    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = slogan.uppercase(),
          style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 7.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor,
            fontFamily = fontStyle.fontFamily
          ),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "2026",
          style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 7.sp,
            color = Color(0xFF94A3B8)
          )
        )
      }

      Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (layout == BrandLayout.ICON_LEFT) Alignment.Start else Alignment.CenterHorizontally
      ) {
        if (layout != BrandLayout.TEXT_ONLY) {
          Icon(
            imageVector = icon.icon,
            contentDescription = null,
            tint = primaryColor,
            modifier = Modifier
              .size(32.dp)
              .padding(bottom = 6.dp)
          )
        }
        Text(
          text = cleanTitle.uppercase(),
          style = androidx.compose.ui.text.TextStyle(
            fontFamily = fontStyle.fontFamily,
            fontWeight = FontWeight.Black,
            color = Color.White,
            fontSize = fontSize.sp,
            lineHeight = (fontSize + 4f).sp
          ),
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
          textAlign = if (layout == BrandLayout.ICON_LEFT) TextAlign.Start else TextAlign.Center
        )
      }

      Column {
        Divider(
          color = primaryColor.copy(alpha = 0.3f),
          thickness = 1.dp,
          modifier = Modifier.padding(bottom = 4.dp)
        )
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "AI GENERATED PREVIEW",
            style = MaterialTheme.typography.bodySmall.copy(
              fontSize = 6.sp,
              color = Color(0xFF94A3B8),
              letterSpacing = 1.sp
            )
          )
          Text(
            text = "BRANDCRAFT",
            style = MaterialTheme.typography.bodySmall.copy(
              fontSize = 6.sp,
              fontWeight = FontWeight.Black,
              color = Color.White
            )
          )
        }
      }
    }
  }
}

// Persisted Local Library of Saved Designs Section
@Composable
fun SavedCreationsSection(
  savedDesigns: List<SavedDesign>,
  onLoad: (SavedDesign) -> Unit,
  onDelete: (SavedDesign) -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 32.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Your Local Creation Library",
      style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Black,
        color = Color.White
      ),
      textAlign = TextAlign.Center
    )
    Text(
      text = "All designs are saved locally in your app's persistent storage. Tap to load or delete.",
      style = MaterialTheme.typography.bodyMedium,
      color = Color(0xFF94A3B8),
      modifier = Modifier.padding(top = 4.dp, bottom = 20.dp),
      textAlign = TextAlign.Center
    )

    if (savedDesigns.isEmpty()) {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 600.dp)
          .testTag("empty_library_card"),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFF334155))
      ) {
        Column(
          modifier = Modifier.padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = Icons.Default.FolderOpen,
            contentDescription = null,
            tint = Color(0xFF64748B),
            modifier = Modifier.size(48.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "No saved creations yet",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Use the customizer tool above to create a design, then click \"Save Design to Library\" to persist it here on your device!",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF94A3B8),
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
          )
        }
      }
    } else {
      // Show horizontally scrolling list of saved designs
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        savedDesigns.forEach { design ->
          Card(
            modifier = Modifier
              .width(220.dp)
              .testTag("saved_design_card_${design.id}")
              .clickable { onLoad(design) },
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color(0xFF334155))
          ) {
            Column(
              modifier = Modifier.padding(12.dp)
            ) {
              // Header title and delete button
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = design.title,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  ),
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis,
                  modifier = Modifier.weight(1f)
                )
                IconButton(
                  onClick = { onDelete(design) },
                  modifier = Modifier.size(24.dp).testTag("delete_saved_design_${design.id}")
                ) {
                  Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete design",
                    tint = Color(0xFFEF4444),
                    modifier = Modifier.size(16.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.height(8.dp))

              // Specs list
              Text(
                text = "Type: ${design.type.label}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF38BDF8),
                fontWeight = FontWeight.SemiBold
              )
              Text(
                text = "Style: ${design.style.label}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8)
              )
              Text(
                text = "Layout: ${design.layout.label}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8)
              )

              Spacer(modifier = Modifier.height(12.dp))

              // A tiny preview block representing the preset colors
              Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Box(
                  modifier = Modifier
                    .size(16.dp)
                    .background(design.colorScheme.primary, shape = CircleShape)
                )
                Box(
                  modifier = Modifier
                    .size(16.dp)
                    .background(design.colorScheme.secondary, shape = CircleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "Palette Preview",
                  style = MaterialTheme.typography.bodySmall,
                  fontSize = 10.sp,
                  color = Color(0xFF64748B)
                )
              }

              Spacer(modifier = Modifier.height(12.dp))

              // Tap indicator
              Box(
                modifier = Modifier
                  .fillMaxWidth()
                  .background(Color(0xFF0F172A), shape = RoundedCornerShape(8.dp))
                  .padding(vertical = 6.dp),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "TAP TO LOAD",
                  style = MaterialTheme.typography.labelSmall.copy(
                    color = Color(0xFF38BDF8),
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 0.5.sp
                  )
                )
              }
            }
          }
        }
      }
    }
  }
}

// Image Display component for rendering generated logos/posters
@Composable
fun ImageDisplay(
  imageUrl: String,
  aspectRatio: Float,
  isImageLoading: Boolean,
  imageErrorMessage: String?,
  onStateChange: (Boolean, String?) -> Unit,
  brandName: String = "AI_Design",
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var isDownloading by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .aspectRatio(aspectRatio)
      .background(Color(0xFF0F172A), shape = RoundedCornerShape(20.dp))
      .border(BorderStroke(1.dp, Color(0xFF334155)), shape = RoundedCornerShape(20.dp))
      .clip(RoundedCornerShape(20.dp))
      .testTag("generated_image_container"),
    contentAlignment = Alignment.Center
  ) {
    coil.compose.AsyncImage(
      model = coil.request.ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build(),
      contentDescription = "AI Generated Design Logo",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
      onState = { state ->
        when (state) {
          is coil.compose.AsyncImagePainter.State.Loading -> {
            onStateChange(true, null)
          }
          is coil.compose.AsyncImagePainter.State.Success -> {
            onStateChange(false, null)
          }
          is coil.compose.AsyncImagePainter.State.Error -> {
            onStateChange(false, "Failed to render image. Tap generate button to retry.")
          }
          else -> {}
        }
      }
    )

    if (isImageLoading) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xCC0F172A)),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          CircularProgressIndicator(
            color = Color(0xFFF43F5E),
            modifier = Modifier.size(44.dp)
          )
          Spacer(modifier = Modifier.height(14.dp))
          Text(
            text = "Crafting Visual Assets...",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Drawing with state-of-the-art Flux generator",
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF64748B)
          )
        }
      }
    }

    imageErrorMessage?.let { err ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color(0xEE0F172A))
          .padding(24.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = Color(0xFFEF4444),
            modifier = Modifier.size(40.dp)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = err,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFEF4444),
            textAlign = TextAlign.Center
          )
        }
      }
    }

    // Download overlay button when image is loaded successfully and not loading
    if (imageUrl.isNotEmpty() && !isImageLoading && imageErrorMessage == null) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
      ) {
        Button(
          onClick = {
            saveOrShareGeneratedImage(
              context = context,
              imageUrl = imageUrl,
              title = brandName,
              shareAfterSave = false,
              onProgressChange = { isDownloading = it }
            )
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xCC0F172A),
            contentColor = Color.White
          ),
          border = BorderStroke(1.dp, Color(0xFF334155)),
          shape = RoundedCornerShape(12.dp),
          contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
          modifier = Modifier
            .testTag("download_logo_button")
            .height(44.dp)
        ) {
          if (isDownloading) {
            CircularProgressIndicator(
              color = Color(0xFFF43F5E),
              modifier = Modifier.size(18.dp),
              strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Saving...",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = Color.White)
            )
          } else {
            Icon(
              imageVector = Icons.Default.Download,
              contentDescription = "Download Logo as PNG",
              tint = Color(0xFF38BDF8),
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Download PNG",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            )
          }
        }
      }
    }
  }
}

// Gemini AI Design Needs Prompt Generator Section
@Composable
fun AiDesignNeedsSection(
  savedAiDesigns: List<SavedAiDesign>,
  onSaveAiDesign: (String, String, String, String?) -> Unit,
  onDeleteAiDesign: (SavedAiDesign) -> Unit,
  onUploadToCloud: (SavedAiDesign, (Boolean) -> Unit) -> Unit
) {
  val context = LocalContext.current
  val coroutineScope = rememberCoroutineScope()

  var recentLogos by remember { mutableStateOf<List<RecentLogo>>(emptyList()) }

  LaunchedEffect(Unit) {
    recentLogos = getRecentLogos(context)
  }

  var brandNameText by remember { mutableStateOf("") }
  var industryText by remember { mutableStateOf("") }
  var designNeedsText by remember { mutableStateOf("") }
  var activeFormTab by remember { mutableStateOf("project") } // Default to the new requested form!
  var projectDescriptionText by remember { mutableStateOf("") }
  var selectedStylePreference by remember { mutableStateOf("Minimalist") }
  var customStyleText by remember { mutableStateOf("") }

  val stylePresets = listOf(
    "Minimalist",
    "Modern Tech",
    "Retro Vintage",
    "Elegant Luxury",
    "Cyberpunk/Neon",
    "Playful Cartoon",
    "Watercolor",
    "Geometric",
    "Custom Style"
  )

  var generatedPrompt by remember { mutableStateOf("") }
  var isGenerating by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  // Image Generation States
  var generatedImageUrl by remember { mutableStateOf("") }
  var imageSeed by remember { mutableStateOf((0..1000000).random()) }
  var isImageLoading by remember { mutableStateOf(false) }
  var imageErrorMessage by remember { mutableStateOf<String?>(null) }
  var selectedType by remember { mutableStateOf(DesignType.LOGO) }
  var isDownloadingImage by remember { mutableStateOf(false) }
  var isUploadingToCloud by remember { mutableStateOf(false) }
  var cloudUploadErrorMessage by remember { mutableStateOf<String?>(null) }

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 32.dp)
      .testTag("ai_design_needs_card"),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    shape = RoundedCornerShape(24.dp),
    border = BorderStroke(1.dp, Color(0xFF334155))
  ) {
    Column(
      modifier = Modifier.padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Icon(
          imageVector = Icons.Default.Brush,
          contentDescription = null,
          tint = Color(0xFF38BDF8),
          modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
          text = "Gemini AI Design Assistant",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Black,
            color = Color.White
          )
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Enter your brand name, industry, and any design preferences below. Gemini AI will analyze your inputs and generate a highly detailed, evocative prompt optimized for creating a professional logo.",
        style = MaterialTheme.typography.bodyMedium,
        color = Color(0xFF94A3B8),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Tab selector for form creation method
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color(0xFF0F172A), RoundedCornerShape(14.dp))
          .border(BorderStroke(1.dp, Color(0xFF334155)), RoundedCornerShape(14.dp))
          .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Box(
          modifier = Modifier
            .weight(1.5f)
            .background(
              if (activeFormTab == "project") Color(0xFF38BDF8) else Color.Transparent,
              RoundedCornerShape(10.dp)
            )
            .clickable { activeFormTab = "project" }
            .padding(vertical = 10.dp)
            .testTag("tab_project_style"),
          contentAlignment = Alignment.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Icon(
              imageVector = Icons.Default.Brush,
              contentDescription = null,
              tint = if (activeFormTab == "project") Color(0xFF0F172A) else Color(0xFF94A3B8),
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Project & Style",
              style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
              color = if (activeFormTab == "project") Color(0xFF0F172A) else Color(0xFF94A3B8)
            )
          }
        }
        Box(
          modifier = Modifier
            .weight(1.3f)
            .background(
              if (activeFormTab == "classic") Color(0xFF38BDF8) else Color.Transparent,
              RoundedCornerShape(10.dp)
            )
            .clickable { activeFormTab = "classic" }
            .padding(vertical = 10.dp)
            .testTag("tab_brand_industry"),
          contentAlignment = Alignment.Center
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = null,
              tint = if (activeFormTab == "classic") Color(0xFF0F172A) else Color(0xFF94A3B8),
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Classic Brand",
              style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
              color = if (activeFormTab == "classic") Color(0xFF0F172A) else Color(0xFF94A3B8)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        if (activeFormTab == "project") {
          // 1. Project / Brand Description
          Column(modifier = Modifier.fillMaxWidth()) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(bottom = 6.dp)
            ) {
              Text(
                text = "Project Description *",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
              )
            }
            OutlinedTextField(
              value = projectDescriptionText,
              onValueChange = { projectDescriptionText = it },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_project_description_input"),
              placeholder = {
                Text(
                  text = "Describe your project, product, or business idea (e.g., 'A modern mobile application for tracking local fitness challenges with interactive leaderboards.')",
                  color = Color(0xFF64748B),
                  style = MaterialTheme.typography.bodyMedium
                )
              },
              minLines = 3,
              maxLines = 6,
              singleLine = false,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedContainerColor = Color(0xFF0F172A),
                unfocusedContainerColor = Color(0xFF0F172A)
              )
            )
          }

          // 2. Design Style Preference
          Column(modifier = Modifier.fillMaxWidth()) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(bottom = 6.dp)
            ) {
              Text(
                text = "Design Style Preference",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
              )
            }

            // Beautiful scrollable style selector
            Row(
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 4.dp)
            ) {
              stylePresets.forEach { style ->
                val isSelected = selectedStylePreference == style
                val chipColor = if (isSelected) Color(0xFF38BDF8) else Color(0xFF0F172A)
                val textColor = if (isSelected) Color(0xFF0F172A) else Color(0xFF94A3B8)
                val borderColor = if (isSelected) Color(0xFF38BDF8) else Color(0xFF334155)

                Box(
                  modifier = Modifier
                    .background(chipColor, RoundedCornerShape(16.dp))
                    .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(16.dp))
                    .clickable { selectedStylePreference = style }
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .testTag("style_chip_$style")
                ) {
                  Text(
                    text = style,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = textColor
                  )
                }
              }
            }

            if (selectedStylePreference == "Custom Style") {
              Spacer(modifier = Modifier.height(12.dp))
              OutlinedTextField(
                value = customStyleText,
                onValueChange = { customStyleText = it },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("ai_custom_style_input"),
                placeholder = {
                  Text(
                    text = "Specify custom design directions (e.g., 'Retro cyberpunk, hand-drawn charcoal aesthetic, or neo-brutalism.')",
                    color = Color(0xFF64748B),
                    style = MaterialTheme.typography.bodyMedium
                  )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                  focusedTextColor = Color.White,
                  unfocusedTextColor = Color.White,
                  focusedBorderColor = Color(0xFF38BDF8),
                  unfocusedBorderColor = Color(0xFF334155),
                  focusedContainerColor = Color(0xFF0F172A),
                  unfocusedContainerColor = Color(0xFF0F172A)
                )
              )
            }
          }
        } else {
          // Classic Brand Identity fields
          Column(modifier = Modifier.fillMaxWidth()) {
            Text(
              text = "Brand Name *",
              style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
              color = Color.White,
              modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
              value = brandNameText,
              onValueChange = { brandNameText = it },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_brand_name_input"),
              placeholder = {
                Text(
                  text = "e.g., EcoGlow, Apex Solutions, Luminis",
                  color = Color(0xFF64748B),
                  style = MaterialTheme.typography.bodyMedium
                )
              },
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedContainerColor = Color(0xFF0F172A),
                unfocusedContainerColor = Color(0xFF0F172A)
              )
            )
          }

          Column(modifier = Modifier.fillMaxWidth()) {
            Text(
              text = "Industry *",
              style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
              color = Color.White,
              modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
              value = industryText,
              onValueChange = { industryText = it },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_industry_input"),
              placeholder = {
                Text(
                  text = "e.g., Organic Skincare, Software Development, Fitness",
                  color = Color(0xFF64748B),
                  style = MaterialTheme.typography.bodyMedium
                )
              },
              singleLine = true,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedContainerColor = Color(0xFF0F172A),
                unfocusedContainerColor = Color(0xFF0F172A)
              )
            )
          }

          Column(modifier = Modifier.fillMaxWidth()) {
            Text(
              text = "Style Preferences / Additional Details (Optional)",
              style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
              color = Color.White,
              modifier = Modifier.padding(bottom = 6.dp)
            )
            OutlinedTextField(
              value = designNeedsText,
              onValueChange = { designNeedsText = it },
              modifier = Modifier
                .fillMaxWidth()
                .testTag("ai_style_notes_textarea"),
              placeholder = {
                Text(
                  text = "e.g., Minimalist leaf emblem, elegant sans-serif, pastel colors, soft gradients, luxury and pure organic aesthetic.",
                  color = Color(0xFF64748B),
                  style = MaterialTheme.typography.bodyMedium
                )
              },
              minLines = 3,
              maxLines = 6,
              singleLine = false,
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedContainerColor = Color(0xFF0F172A),
                unfocusedContainerColor = Color(0xFF0F172A)
              )
            )
          }
        }

        // Shared Design Type Selector inside the form card (Logo or Poster)
        Column(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "Generate as Logo or Poster? *",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.padding(bottom = 6.dp)
          )
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0xFF0F172A), RoundedCornerShape(12.dp))
              .border(BorderStroke(1.dp, Color(0xFF334155)), RoundedCornerShape(12.dp))
              .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            listOf(DesignType.LOGO, DesignType.POSTER).forEach { type ->
              val isSelected = selectedType == type
              Box(
                modifier = Modifier
                  .weight(1f)
                  .background(
                    if (isSelected) Color(0xFFF43F5E) else Color.Transparent,
                    RoundedCornerShape(8.dp)
                  )
                  .clickable { selectedType = type }
                  .padding(vertical = 10.dp)
                  .testTag("form_type_tab_${type.name.lowercase()}"),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = if (type == DesignType.LOGO) "Logo (1:1 Aspect Ratio)" else "Poster (3:4 Aspect Ratio)",
                  style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                  color = if (isSelected) Color.White else Color(0xFF94A3B8)
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      errorMessage?.let { error ->
        Text(
          text = error,
          color = Color(0xFFEF4444),
          style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
          modifier = Modifier.padding(bottom = 12.dp)
        )
      }

      Button(
        onClick = {
          if (activeFormTab == "project") {
            if (projectDescriptionText.isBlank()) {
              errorMessage = "Please enter your project description!"
              return@Button
            }
          } else {
            if (brandNameText.isBlank()) {
              errorMessage = "Please enter your brand name!"
              return@Button
            }
            if (industryText.isBlank()) {
              errorMessage = "Please enter your industry!"
              return@Button
            }
          }
          errorMessage = null
          isGenerating = true
          coroutineScope.launch {
            val result = if (activeFormTab == "project") {
              val styleString = if (selectedStylePreference == "Custom Style") customStyleText else selectedStylePreference
              generateDesignPrompt(
                projectDescription = projectDescriptionText,
                stylePreference = styleString,
                designType = selectedType.label
              )
            } else {
              generateLogoPrompt(
                brandName = brandNameText,
                industry = industryText,
                styleNotes = designNeedsText
              )
            }
            isGenerating = false
            if (result.startsWith("Error:")) {
              errorMessage = result
            } else {
              generatedPrompt = result
              generatedImageUrl = ""
              imageErrorMessage = null
            }
          }
        },
        enabled = !isGenerating,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFF38BDF8),
          disabledContainerColor = Color(0xFF334155)
        ),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .testTag("generate_ai_prompt_btn")
      ) {
        if (isGenerating) {
          CircularProgressIndicator(
            color = Color(0xFF0F172A),
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Analyzing with Gemini...",
            style = MaterialTheme.typography.labelLarge.copy(
              color = Color(0xFF0F172A),
              fontWeight = FontWeight.Bold
            )
          )
        } else {
          Icon(
            imageVector = Icons.Default.Build,
            contentDescription = null,
            tint = Color(0xFF0F172A),
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Generate Prompt with AI",
            style = MaterialTheme.typography.labelLarge.copy(
              color = Color(0xFF0F172A),
              fontWeight = FontWeight.Bold
            )
          )
        }
      }

      if (generatedPrompt.isNotEmpty()) {
        Spacer(modifier = Modifier.height(24.dp))

        Divider(color = Color(0xFF334155), thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        Column(
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF10B981),
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Optimized Image Generation Prompt:",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF10B981)
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0xFF0F172A), shape = RoundedCornerShape(16.dp))
              .border(BorderStroke(1.dp, Color(0xFF334155)), shape = RoundedCornerShape(16.dp))
              .padding(16.dp)
              .testTag("generated_prompt_textbox")
          ) {
            Text(
              text = generatedPrompt,
              style = MaterialTheme.typography.bodyMedium,
              color = Color.White,
              lineHeight = 22.sp
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier
                .background(Color(0xFF0F172A), RoundedCornerShape(12.dp))
                .padding(4.dp)
            ) {
              listOf(DesignType.LOGO, DesignType.POSTER).forEach { type ->
                val isSelected = selectedType == type
                Box(
                  modifier = Modifier
                    .background(
                      if (isSelected) Color(0xFF38BDF8) else Color.Transparent,
                      RoundedCornerShape(8.dp)
                    )
                    .clickable { selectedType = type }
                    .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                  Text(
                    text = if (type == DesignType.LOGO) "Logo (1:1)" else "Poster (3:4)",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = if (isSelected) Color(0xFF0F172A) else Color(0xFF94A3B8)
                  )
                }
              }
            }

            Button(
              onClick = {
                val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clip = android.content.ClipData.newPlainText("AI Generated Prompt", generatedPrompt)
                clipboard.setPrimaryClip(clip)
                android.widget.Toast.makeText(context, "Copied prompt to clipboard!", android.widget.Toast.LENGTH_SHORT).show()
              },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.testTag("copy_prompt_btn")
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Share,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Copy Prompt",
                  style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                  )
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(24.dp))

          Button(
            onClick = {
              imageErrorMessage = null
              isImageLoading = true
              val encoded = android.net.Uri.encode(generatedPrompt)
              val width = if (selectedType == DesignType.LOGO) 1024 else 768
              val height = if (selectedType == DesignType.LOGO) 1024 else 1024
              generatedImageUrl = "https://image.pollinations.ai/p/$encoded?width=$width&height=$height&nologo=true&seed=$imageSeed"
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFF43F5E),
              disabledContainerColor = Color(0xFF334155)
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("generate_ai_image_btn")
          ) {
            Icon(
              imageVector = Icons.Default.Brush,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = if (generatedImageUrl.isEmpty()) "Generate AI Logo / Poster Image" else "Re-generate / New Variation",
              style = MaterialTheme.typography.labelLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
              )
            )
          }

          if (generatedImageUrl.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))

            ImageDisplay(
              imageUrl = generatedImageUrl,
              aspectRatio = if (selectedType == DesignType.LOGO) 1f else 0.75f,
              isImageLoading = isImageLoading,
              imageErrorMessage = imageErrorMessage,
              brandName = if (activeFormTab == "project") "Project Design" else brandNameText.ifEmpty { "AI_Design" },
              onStateChange = { loading, error ->
                isImageLoading = loading
                imageErrorMessage = error
                if (!loading && error == null && generatedImageUrl.isNotEmpty()) {
                  if (recentLogos.firstOrNull()?.imageUrl != generatedImageUrl) {
                    val newLogo = RecentLogo(
                      id = System.currentTimeMillis().toString(),
                      brandName = if (activeFormTab == "project") "Project Design" else brandNameText.ifEmpty { "AI_Design" },
                      industry = if (activeFormTab == "project") selectedStylePreference else industryText.ifEmpty { "General" },
                      prompt = generatedPrompt,
                      imageUrl = generatedImageUrl,
                      timestamp = System.currentTimeMillis()
                    )
                    recentLogos = saveRecentLogo(context, newLogo)
                  }
                }
              }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Button(
                onClick = {
                  imageSeed = (0..1000000).random()
                  isImageLoading = true
                  val encoded = android.net.Uri.encode(generatedPrompt)
                  val width = if (selectedType == DesignType.LOGO) 1024 else 768
                  val height = if (selectedType == DesignType.LOGO) 1024 else 1024
                  generatedImageUrl = "https://image.pollinations.ai/p/$encoded?width=$width&height=$height&nologo=true&seed=$imageSeed"
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(44.dp)
                  .testTag("regenerate_seed_btn")
              ) {
                Icon(
                  imageVector = Icons.Default.Refresh,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Variant",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = Color.White
                )
              }

              Button(
                onClick = {
                  val title = if (brandNameText.isNotEmpty()) brandNameText else "AI_Design"
                  saveOrShareGeneratedImage(
                    context = context,
                    imageUrl = generatedImageUrl,
                    title = title,
                    shareAfterSave = false,
                    onProgressChange = { isDownloadingImage = it }
                  )
                },
                enabled = !isDownloadingImage && !isImageLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(1.2f)
                  .height(44.dp)
                  .testTag("save_ai_image_btn")
              ) {
                if (isDownloadingImage) {
                  CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                  )
                } else {
                  Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "Save",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                  )
                }
              }

              Button(
                onClick = {
                  val title = if (brandNameText.isNotEmpty()) brandNameText else "AI_Design"
                  saveOrShareGeneratedImage(
                    context = context,
                    imageUrl = generatedImageUrl,
                    title = title,
                    shareAfterSave = true,
                    onProgressChange = { isDownloadingImage = it }
                  )
                },
                enabled = !isDownloadingImage && !isImageLoading,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(1.2f)
                  .height(44.dp)
                  .testTag("share_ai_image_btn")
              ) {
                Icon(
                  imageVector = Icons.Default.Share,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Share",
                  style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                  color = Color.White
                )
              }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
              onClick = {
                val title = if (brandNameText.isNotEmpty()) brandNameText else "AI_Design"
                uploadImageToFirebaseStorage(
                  context = context,
                  imageUrl = generatedImageUrl,
                  title = title,
                  onProgressChange = { isUploadingToCloud = it },
                  onComplete = { cloudUrl, errorMsg ->
                    if (cloudUrl != null) {
                      onSaveAiDesign(generatedPrompt, title, generatedImageUrl, cloudUrl)
                      cloudUploadErrorMessage = null
                    } else {
                      onSaveAiDesign(generatedPrompt, title, generatedImageUrl, null)
                      cloudUploadErrorMessage = errorMsg
                    }
                  }
                )
              },
              enabled = !isUploadingToCloud && !isImageLoading,
              colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6366F1),
                disabledContainerColor = Color(0xFF334155)
              ),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("backup_to_cloud_btn")
            ) {
              if (isUploadingToCloud) {
                CircularProgressIndicator(
                  color = Color.White,
                  modifier = Modifier.size(18.dp),
                  strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Saving to Cloud...",
                  style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = Color.White)
                )
              } else {
                Icon(
                  imageVector = Icons.Default.CloudUpload,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Save Design to Cloud Library",
                  style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = Color.White)
                )
              }
            }

            if (com.google.firebase.FirebaseApp.getApps(context).isEmpty()) {
              Spacer(modifier = Modifier.height(10.dp))
              Card(
                colors = CardDefaults.cardColors(containerColor = Color(0x15F59E0B)),
                border = BorderStroke(1.dp, Color(0x33F59E0B)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
              ) {
                Row(
                  modifier = Modifier.padding(10.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color(0xFFF59E0B),
                    modifier = Modifier.size(16.dp)
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = "Cloud backup is running in local fallback mode. To enable Firebase Storage, please add google-services.json to the app module.",
                    style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFF59E0B)),
                    lineHeight = 14.sp
                  )
                }
              }
            }
          }

          if (recentLogos.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Divider(color = Color(0xFF334155), thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))

            Text(
              text = "Recent Designs",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Black,
                color = Color.White
              ),
              modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "Quick access to your last 5 generated logos. Tap any to restore inputs or view prompt.",
              style = MaterialTheme.typography.bodySmall,
              color = Color(0xFF94A3B8),
              modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              recentLogos.forEach { logo ->
                Card(
                  modifier = Modifier
                    .width(200.dp)
                    .testTag("recent_logo_card_${logo.id}")
                    .clickable {
                      brandNameText = logo.brandName
                      industryText = logo.industry
                      designNeedsText = logo.prompt
                      generatedPrompt = logo.prompt
                      generatedImageUrl = logo.imageUrl
                      android.widget.Toast.makeText(context, "Restored inputs and preview for ${logo.brandName}!", android.widget.Toast.LENGTH_SHORT).show()
                    },
                  colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                  shape = RoundedCornerShape(16.dp),
                  border = BorderStroke(1.dp, Color(0xFF334155))
                ) {
                  Column(
                    modifier = Modifier.padding(12.dp)
                  ) {
                    Box(
                      modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1E293B)),
                      contentAlignment = Alignment.Center
                    ) {
                      coil.compose.AsyncImage(
                        model = coil.request.ImageRequest.Builder(LocalContext.current)
                          .data(logo.imageUrl)
                          .crossfade(true)
                          .build(),
                        contentDescription = "Recent Design Logo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                      )

                      // Industry Badge
                      Box(
                        modifier = Modifier
                          .align(Alignment.BottomStart)
                          .padding(6.dp)
                          .background(Color(0xE60F172A), RoundedCornerShape(6.dp))
                          .padding(horizontal = 6.dp, vertical = 2.dp)
                      ) {
                        Text(
                          text = logo.industry,
                          style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 8.sp,
                            color = Color(0xFF38BDF8),
                            fontWeight = FontWeight.Bold
                          ),
                          maxLines = 1,
                          overflow = TextOverflow.Ellipsis
                        )
                      }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                      text = logo.brandName,
                      style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                      ),
                      maxLines = 1,
                      overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                      text = logo.prompt,
                      style = MaterialTheme.typography.labelSmall,
                      color = Color(0xFF94A3B8),
                      maxLines = 2,
                      overflow = TextOverflow.Ellipsis,
                      lineHeight = 12.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                      Button(
                        onClick = {
                          brandNameText = logo.brandName
                          industryText = logo.industry
                          designNeedsText = logo.prompt
                          generatedPrompt = logo.prompt
                          generatedImageUrl = logo.imageUrl
                          android.widget.Toast.makeText(context, "Restored inputs & preview!", android.widget.Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF334155)),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp),
                        modifier = Modifier
                          .weight(1f)
                          .height(32.dp)
                      ) {
                        Text(
                          text = "Restore",
                          style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 9.sp
                          )
                        )
                      }

                      IconButton(
                        onClick = {
                          val updated = recentLogos.filterNot { it.id == logo.id }
                          recentLogos = updated
                          val prefs = context.getSharedPreferences("BrandCraftPrefs", android.content.Context.MODE_PRIVATE)
                          val serialized = updated.joinToString("\n") { it.toPersistableString() }
                          prefs.edit().putString("recent_logos_data", serialized).apply()
                        },
                        modifier = Modifier
                          .size(32.dp)
                          .background(Color(0x22EF4444), RoundedCornerShape(8.dp))
                      ) {
                        Icon(
                          imageVector = Icons.Default.Delete,
                          contentDescription = "Delete",
                          tint = Color(0xFFEF4444),
                          modifier = Modifier.size(14.dp)
                        )
                      }
                    }
                  }
                }
              }
            }
          }

          if (savedAiDesigns.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Divider(color = Color(0xFF334155), thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))

            Text(
              text = "Your AI Creations Library",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Black,
                color = Color.White
              ),
              modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "All your cloud-saved & locally-cached AI designs. Click Sync to back up local items.",
              style = MaterialTheme.typography.bodySmall,
              color = Color(0xFF94A3B8),
              modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
              horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              savedAiDesigns.forEach { design ->
                var isBackupPending by remember { mutableStateOf(false) }
                Card(
                  modifier = Modifier
                    .width(200.dp)
                    .testTag("saved_ai_design_card_${design.id}"),
                  colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
                  shape = RoundedCornerShape(16.dp),
                  border = BorderStroke(1.dp, Color(0xFF334155))
                ) {
                  Column(
                    modifier = Modifier.padding(12.dp)
                  ) {
                    Box(
                      modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1E293B)),
                      contentAlignment = Alignment.Center
                    ) {
                      coil.compose.AsyncImage(
                        model = coil.request.ImageRequest.Builder(LocalContext.current)
                          .data(design.cloudUrl ?: design.imageUrl)
                          .crossfade(true)
                          .build(),
                        contentDescription = "AI Saved Design",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                      )

                      // Badge for Cloud Sync status
                      Box(
                        modifier = Modifier
                          .align(Alignment.TopEnd)
                          .padding(6.dp)
                          .background(Color(0xCC0F172A), RoundedCornerShape(6.dp))
                          .padding(4.dp)
                      ) {
                        Row(
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                          Icon(
                            imageVector = if (design.cloudUrl != null) Icons.Default.CloudDone else Icons.Default.Cloud,
                            contentDescription = if (design.cloudUrl != null) "Cloud Saved" else "Local Only",
                            tint = if (design.cloudUrl != null) Color(0xFF10B981) else Color(0xFF94A3B8),
                            modifier = Modifier.size(12.dp)
                          )
                          Text(
                            text = if (design.cloudUrl != null) "Cloud" else "Local",
                            style = MaterialTheme.typography.labelSmall.copy(
                              fontSize = 8.sp,
                              color = if (design.cloudUrl != null) Color(0xFF10B981) else Color(0xFF94A3B8),
                              fontWeight = FontWeight.Bold
                            )
                          )
                        }
                      }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                      text = design.title,
                      style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                      ),
                      maxLines = 1,
                      overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                      text = design.prompt,
                      style = MaterialTheme.typography.labelSmall,
                      color = Color(0xFF94A3B8),
                      maxLines = 2,
                      overflow = TextOverflow.Ellipsis,
                      lineHeight = 12.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.spacedBy(6.dp),
                      verticalAlignment = Alignment.CenterVertically
                    ) {
                      if (design.cloudUrl == null) {
                        IconButton(
                          onClick = {
                            onUploadToCloud(design) { isBackupPending = it }
                          },
                          enabled = !isBackupPending,
                          modifier = Modifier
                            .weight(1f)
                            .height(32.dp)
                            .background(Color(0xFF38BDF8), RoundedCornerShape(8.dp))
                        ) {
                          if (isBackupPending) {
                            CircularProgressIndicator(
                              color = Color.White,
                              modifier = Modifier.size(12.dp),
                              strokeWidth = 1.5.dp
                            )
                          } else {
                            Row(
                              verticalAlignment = Alignment.CenterVertically,
                              horizontalArrangement = Arrangement.Center
                            ) {
                              Icon(
                                imageVector = Icons.Default.CloudUpload,
                                contentDescription = "Backup",
                                tint = Color(0xFF0F172A),
                                modifier = Modifier.size(12.dp)
                              )
                              Spacer(modifier = Modifier.width(4.dp))
                              Text(
                                text = "Sync",
                                style = MaterialTheme.typography.labelSmall.copy(
                                  fontWeight = FontWeight.Bold,
                                  color = Color(0xFF0F172A),
                                  fontSize = 9.sp
                                )
                              )
                            }
                          }
                        }
                      } else {
                        IconButton(
                          onClick = {
                            val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                            val clip = android.content.ClipData.newPlainText("AI Generated Cloud URL", design.cloudUrl)
                            clipboard.setPrimaryClip(clip)
                            android.widget.Toast.makeText(context, "Copied Cloud URL to clipboard!", android.widget.Toast.LENGTH_SHORT).show()
                          },
                          modifier = Modifier
                            .weight(1f)
                            .height(32.dp)
                            .background(Color(0xFF334155), RoundedCornerShape(8.dp))
                        ) {
                          Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                          ) {
                            Icon(
                              imageVector = Icons.Default.Share,
                              contentDescription = "Copy Link",
                              tint = Color.White,
                              modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                              text = "Link",
                              style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 9.sp
                              )
                            )
                          }
                        }
                      }

                      IconButton(
                        onClick = { onDeleteAiDesign(design) },
                        modifier = Modifier
                          .size(32.dp)
                          .background(Color(0x22EF4444), RoundedCornerShape(8.dp))
                      ) {
                        Icon(
                          imageVector = Icons.Default.Delete,
                          contentDescription = "Delete from local list",
                          tint = Color(0xFFEF4444),
                          modifier = Modifier.size(14.dp)
                        )
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

// "How It Works" Section
@Composable
fun HowItWorksSection() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 48.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "How BrandCraft Works",
      style = MaterialTheme.typography.displayMedium,
      color = Color.White,
      textAlign = TextAlign.Center
    )
    Text(
      text = "High-quality poster and logo creation in under 2 minutes. Follow these 3 easy steps.",
      style = MaterialTheme.typography.bodyLarge,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .padding(top = 8.dp, bottom = 40.dp)
        .widthIn(max = 520.dp)
    )

    // Three steps grid/layout
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 900.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      val steps = listOf(
        Triple("1", "Choose Category", "Select between professional high-resolution Logo marks or beautiful event / promotional Poster layouts."),
        Triple("2", "Customize in Seconds", "Type your brand, custom dates, or taglines. Play with dynamic, AI-optimized aesthetic themes instantly."),
        Triple("3", "Download Instantly", "Hit export to download high-resolution vectors, PDF-ready print layouts, and transparent PNGs. Commercial license active!")
      )

      BoxWithConstraints {
        val isWide = maxWidth > 650.dp
        if (isWide) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            steps.forEach { (num, title, text) ->
              StepCard(num = num, title = title, text = text, modifier = Modifier.weight(1f))
            }
          }
        } else {
          Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            steps.forEach { (num, title, text) ->
              StepCard(num = num, title = title, text = text, modifier = Modifier.fillMaxWidth())
            }
          }
        }
      }
    }
  }
}

// Individual Step Card Composable
@Composable
fun StepCard(num: String, title: String, text: String, modifier: Modifier = Modifier) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
    shape = RoundedCornerShape(16.dp),
    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
  ) {
    Column(
      modifier = Modifier.padding(20.dp)
    ) {
      // Glow index badge
      Box(
        modifier = Modifier
          .size(38.dp)
          .background(
            brush = Brush.linearGradient(listOf(Color(0xFF7C3AED), Color(0xFF60A5FA))),
            shape = CircleShape
          ),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = num,
          style = MaterialTheme.typography.titleMedium.copy(
            color = Color.White,
            fontWeight = FontWeight.Black
          )
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      )

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = Color(0xFF94A3B8),
          lineHeight = 20.sp
        )
      )
    }
  }
}

// "Why Us" Section
@Composable
fun WhyUsSection() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 48.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Designed For Growth",
      style = MaterialTheme.typography.displayMedium,
      color = Color.White,
      textAlign = TextAlign.Center
    )
    Text(
      text = "Get professional agency-grade branding assets at ultra-low prices with zero experience required.",
      style = MaterialTheme.typography.bodyLarge,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .padding(top = 8.dp, bottom = 40.dp)
        .widthIn(max = 520.dp)
    )

    BoxWithConstraints {
      val isWide = maxWidth > 700.dp
      if (isWide) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 900.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          FeatureCard(
            icon = Icons.Default.Sell,
            title = "Actually Affordable",
            desc = "Avoid high freelancer retainer budgets. Get professional, print-ready, high-resolution vector assets at unbeatable, budget-friendly low rates.",
            colorGlow = Color(0xFF38BDF8),
            modifier = Modifier.weight(1f)
          )
          FeatureCard(
            icon = Icons.Default.LocalActivity,
            title = "Commercial Rights",
            desc = "Once exported, you enjoy complete legal ownership. Commercial rights included to safely print posters, launch physical storefronts, and publish trademarked logos.",
            colorGlow = Color(0xFFC084FC),
            modifier = Modifier.weight(1f)
          )
          FeatureCard(
            icon = Icons.Default.Speed,
            title = "Zero Experience Needed",
            desc = "Forget complex editing software. Our intuitive prompt-to-design technology and adaptive templates guide you to instant branding success in seconds.",
            colorGlow = Color(0xFFF43F5E),
            modifier = Modifier.weight(1f)
          )
        }
      } else {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 500.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          FeatureCard(
            icon = Icons.Default.Sell,
            title = "Actually Affordable",
            desc = "Avoid high freelancer retainer budgets. Get professional, print-ready, high-resolution vector assets at unbeatable, budget-friendly low rates.",
            colorGlow = Color(0xFF38BDF8),
            modifier = Modifier.fillMaxWidth()
          )
          FeatureCard(
            icon = Icons.Default.LocalActivity,
            title = "Commercial Rights",
            desc = "Once exported, you enjoy complete legal ownership. Commercial rights included to safely print posters, launch physical storefronts, and publish trademarked logos.",
            colorGlow = Color(0xFFC084FC),
            modifier = Modifier.fillMaxWidth()
          )
          FeatureCard(
            icon = Icons.Default.Speed,
            title = "Zero Experience Needed",
            desc = "Forget complex editing software. Our intuitive prompt-to-design technology and adaptive templates guide you to instant branding success in seconds.",
            colorGlow = Color(0xFFF43F5E),
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

// Why Us Feature Card Component
@Composable
fun FeatureCard(
  icon: ImageVector,
  title: String,
  desc: String,
  colorGlow: Color,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
    shape = RoundedCornerShape(18.dp),
    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
  ) {
    Column(
      modifier = Modifier.padding(24.dp)
    ) {
      // Glow Ring icon container
      Box(
        modifier = Modifier
          .size(44.dp)
          .background(colorGlow.copy(alpha = 0.15f), shape = RoundedCornerShape(12.dp))
          .border(1.dp, colorGlow.copy(alpha = 0.4f), shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = colorGlow,
          modifier = Modifier.size(20.dp)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.ExtraBold,
          color = Color.White
        )
      )

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = desc,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = Color(0xFF94A3B8),
          lineHeight = 22.sp
        )
      )
    }
  }
}

// Social Proof / Gallery templates Composable
@Composable
fun SocialProofGallerySection(onTemplateClick: (String, DesignType, DesignStyle) -> Unit) {
  val mockTemplates = listOf(
    // (Title, DesignType, DesignStyle, TagLine, Subtag)
    Triple("JAZZ CLUB", DesignType.POSTER, DesignStyle.RETRO),
    Triple("METRO LAB", DesignType.LOGO, DesignStyle.CYBERPUNK),
    Triple("CREATIVE", DesignType.LOGO, DesignStyle.MINIMALIST),
    Triple("SWISS ART", DesignType.POSTER, DesignStyle.SWISS),
    Triple("GLOW CONCERT", DesignType.POSTER, DesignStyle.CYBERPUNK),
    Triple("LUXE COFE", DesignType.LOGO, DesignStyle.MINIMALIST)
  )

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 48.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Stunning Visual Templates",
      style = MaterialTheme.typography.displayMedium,
      color = Color.White,
      textAlign = TextAlign.Center
    )
    Text(
      text = "Click any curated template below to load and customize it instantly in our live editor!",
      style = MaterialTheme.typography.bodyLarge,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .padding(top = 8.dp, bottom = 40.dp)
        .widthIn(max = 520.dp)
    )

    // 2x3 responsive Grid
    BoxWithConstraints {
      val columns = if (maxWidth > 600.dp) 3 else 2
      val rowsCount = (mockTemplates.size + columns - 1) / columns

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .widthIn(max = 900.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        for (r in 0 until rowsCount) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            for (c in 0 until columns) {
              val idx = r * columns + c
              if (idx < mockTemplates.size) {
                val (title, type, style) = mockTemplates[idx]
                Box(
                  modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFF1E293B), shape = RoundedCornerShape(16.dp))
                    .border(1.dp, Color(0xFF334155), shape = RoundedCornerShape(16.dp))
                    .clickable { onTemplateClick(title, type, style) }
                    .padding(12.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Small preview canvas representation
                    Box(
                      modifier = Modifier
                        .size(100.dp, 120.dp)
                        .background(Color(0xFF0F172A), shape = RoundedCornerShape(8.dp))
                        .padding(6.dp),
                      contentAlignment = Alignment.Center
                    ) {
                      if (type == DesignType.POSTER) {
                        PosterCanvas(title = title, style = style)
                      } else {
                        LogoCanvas(title = title, style = style)
                      }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                      text = title,
                      style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                      )
                    )

                    // Tags indicating type and style
                    Row(
                      modifier = Modifier.padding(top = 4.dp),
                      horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                      Box(
                        modifier = Modifier
                          .background(Color(0xFF0F172A), shape = RoundedCornerShape(4.dp))
                          .padding(horizontal = 6.dp, vertical = 2.dp)
                      ) {
                        Text(
                          text = type.label.uppercase(),
                          style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 8.sp,
                            color = Color(0xFF38BDF8)
                          )
                        )
                      }
                      Box(
                        modifier = Modifier
                          .background(Color(0xFF0F172A), shape = RoundedCornerShape(4.dp))
                          .padding(horizontal = 6.dp, vertical = 2.dp)
                      ) {
                        Text(
                          text = style.label.uppercase(),
                          style = MaterialTheme.typography.labelSmall.copy(
                            fontSize = 8.sp,
                            color = Color(0xFFC084FC)
                          )
                        )
                      }
                    }
                  }
                }
              } else {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(48.dp))

    // Testimonials grid
    Text(
      text = "Loved by Creative Founders Worldwide",
      style = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.Black,
        color = Color.White
      )
    )

    Spacer(modifier = Modifier.height(24.dp))

    BoxWithConstraints {
      val isWide = maxWidth > 650.dp
      if (isWide) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 900.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          TestimonialCard(
            quote = "“As a small café owner, I didn’t have $500 for a logo. BrandCraft gave me a stunning retro logo and daily sandwich board posters in under 2 minutes for pennies.”",
            author = "Marcus Thorne",
            role = "Founder, The Daily Grind",
            modifier = Modifier.weight(1f)
          )
          TestimonialCard(
            quote = "“The speed is jaw-dropping. I just type my event name, swipe through cyberpunk glowing themes, and print the high-res file immediately. Unbeatable value!”",
            author = "Elena Rostova",
            role = "Tech Event Organizer",
            modifier = Modifier.weight(1f)
          )
        }
      } else {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 500.dp),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          TestimonialCard(
            quote = "“As a small café owner, I didn’t have $500 for a logo. BrandCraft gave me a stunning retro logo and daily sandwich board posters in under 2 minutes for pennies.”",
            author = "Marcus Thorne",
            role = "Founder, The Daily Grind",
            modifier = Modifier.fillMaxWidth()
          )
          TestimonialCard(
            quote = "“The speed is jaw-dropping. I just type my event name, swipe through cyberpunk glowing themes, and print the high-res file immediately. Unbeatable value!”",
            author = "Elena Rostova",
            role = "Tech Event Organizer",
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

// Individual Testimonial Card Composable
@Composable
fun TestimonialCard(quote: String, author: String, role: String, modifier: Modifier = Modifier) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
    shape = RoundedCornerShape(16.dp),
    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
  ) {
    Column(modifier = Modifier.padding(20.dp)) {
      Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(5) {
          Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = Color(0xFFFBBF24),
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = quote,
        style = MaterialTheme.typography.bodyMedium.copy(
          fontStyle = FontStyle.Italic,
          color = Color(0xFFE2E8F0),
          lineHeight = 22.sp
        )
      )

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = author,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      )
      Text(
        text = role,
        style = MaterialTheme.typography.bodySmall.copy(
          color = Color(0xFF94A3B8)
        )
      )
    }
  }
}

// Interactive Pricing Tier Section
@Composable
fun PricingSection(onSelectTier: (String, String) -> Unit) {
  var isAnnual by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 48.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Simple, Honest Pricing",
      style = MaterialTheme.typography.displayMedium,
      color = Color.White,
      textAlign = TextAlign.Center
    )
    Text(
      text = "Start free to explore and build, or upgrade for unlimited high-res vector exports.",
      style = MaterialTheme.typography.bodyLarge,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .padding(top = 8.dp, bottom = 24.dp)
        .widthIn(max = 520.dp)
    )

    // Monthly / Annual toggle switch
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(bottom = 40.dp)
    ) {
      Text(
        text = "Monthly",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.SemiBold,
          color = if (isAnnual) Color(0xFF94A3B8) else Color.White
        )
      )
      Spacer(modifier = Modifier.width(12.dp))
      Switch(
        checked = isAnnual,
        onCheckedChange = { isAnnual = it },
        colors = SwitchDefaults.colors(
          checkedThumbColor = Color(0xFF38BDF8),
          checkedTrackColor = Color(0x3338BDF8),
          uncheckedThumbColor = Color(0xFF94A3B8),
          uncheckedTrackColor = Color(0x3394A3B8)
        ),
        modifier = Modifier.testTag("pricing_annual_switch")
      )
      Spacer(modifier = Modifier.width(12.dp))
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "Annual Billing",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.SemiBold,
            color = if (isAnnual) Color.White else Color(0xFF94A3B8)
          )
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
          modifier = Modifier
            .background(Color(0xFF0284C7), shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
          Text(
            text = "SAVE 20%",
            style = MaterialTheme.typography.labelSmall.copy(
              fontSize = 9.sp,
              color = Color.White
            )
          )
        }
      }
    }

    // 3 Simple Tiers layout
    BoxWithConstraints {
      val isWide = maxWidth > 750.dp
      if (isWide) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 940.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          PricingCard(
            title = "Free Trial",
            price = "$0",
            period = "forever",
            features = listOf(
              "Full dynamic editor access",
              "3 free watermarked downloads",
              "10+ starter templates included",
              "Client-side local draft saving"
            ),
            ctaText = "Start Free Trial",
            isPopular = false,
            onSelect = { onSelectTier("Free Trial", "$0") },
            modifier = Modifier.weight(1f)
          )

          PricingCard(
            title = "Creator Plan",
            price = if (isAnnual) "$7" else "$9",
            period = "month",
            features = listOf(
              "Unlimited Poster designs",
              "No watermarks on downloads",
              "High-res PNG & JPEG exports",
              "Commercial usage license",
              "Priority rendering speeds"
            ),
            ctaText = "Get Creator Plan",
            isPopular = true,
            onSelect = { onSelectTier("Creator Plan", if (isAnnual) "$7" else "$9") },
            modifier = Modifier.weight(1.05f) // visually popped
          )

          PricingCard(
            title = "Pro Brand Plan",
            price = if (isAnnual) "$15" else "$19",
            period = "month",
            features = listOf(
              "Unlimited Posters AND Logos",
              "Infinite vector (SVG, PDF) formats",
              "Complete brand identity kits",
              "Full commercial copyright",
              "24/7 dedicated support priority"
            ),
            ctaText = "Get Pro Brand Plan",
            isPopular = false,
            onSelect = { onSelectTier("Pro Brand Plan", if (isAnnual) "$15" else "$19") },
            modifier = Modifier.weight(1f)
          )
        }
      } else {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 420.dp),
          verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
          PricingCard(
            title = "Free Trial",
            price = "$0",
            period = "forever",
            features = listOf(
              "Full dynamic editor access",
              "3 free watermarked downloads",
              "10+ starter templates included",
              "Client-side local draft saving"
            ),
            ctaText = "Start Free Trial",
            isPopular = false,
            onSelect = { onSelectTier("Free Trial", "$0") },
            modifier = Modifier.fillMaxWidth()
          )

          PricingCard(
            title = "Creator Plan",
            price = if (isAnnual) "$7" else "$9",
            period = "month",
            features = listOf(
              "Unlimited Poster designs",
              "No watermarks on downloads",
              "High-res PNG & JPEG exports",
              "Commercial usage license",
              "Priority rendering speeds"
            ),
            ctaText = "Get Creator Plan",
            isPopular = true,
            onSelect = { onSelectTier("Creator Plan", if (isAnnual) "$7" else "$9") },
            modifier = Modifier.fillMaxWidth()
          )

          PricingCard(
            title = "Pro Brand Plan",
            price = if (isAnnual) "$15" else "$19",
            period = "month",
            features = listOf(
              "Unlimited Posters AND Logos",
              "Infinite vector (SVG, PDF) formats",
              "Complete brand identity kits",
              "Full commercial copyright",
              "24/7 dedicated support priority"
            ),
            ctaText = "Get Pro Brand Plan",
            isPopular = false,
            onSelect = { onSelectTier("Pro Brand Plan", if (isAnnual) "$15" else "$19") },
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

// Pricing Card Composable
@Composable
fun PricingCard(
  title: String,
  price: String,
  period: String,
  features: List<String>,
  ctaText: String,
  isPopular: Boolean,
  onSelect: () -> Unit,
  modifier: Modifier = Modifier
) {
  val borderStroke = if (isPopular) {
    BorderStroke(2.dp, Brush.linearGradient(listOf(Color(0xFF7C3AED), Color(0xFF60A5FA))))
  } else {
    BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
  }

  Card(
    modifier = modifier
      .shadow(
        elevation = if (isPopular) 16.dp else 0.dp,
        shape = RoundedCornerShape(20.dp),
        clip = false,
        ambientColor = Color(0xFFA78BFA).copy(alpha = 0.5f),
        spotColor = Color(0xFF60A5FA).copy(alpha = 0.5f)
      )
      .testTag("pricing_card_${title.lowercase().replace(" ", "_")}"),
    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
    shape = RoundedCornerShape(20.dp),
    border = borderStroke
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)
    ) {
      if (isPopular) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.ExtraBold,
              color = Color.White
            )
          )
          Box(
            modifier = Modifier
              .background(
                Brush.linearGradient(listOf(Color(0xFF38BDF8), Color(0xFFC084FC))),
                shape = RoundedCornerShape(8.dp)
              )
              .padding(horizontal = 8.dp, vertical = 4.dp)
          ) {
            Text(
              text = "BEST VALUE",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                color = Color(0xFF0F172A),
                fontWeight = FontWeight.Black
              )
            )
          }
        }
      } else {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color(0xFF94A3B8)
          )
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      Row(verticalAlignment = Alignment.Bottom) {
        Text(
          text = price,
          style = MaterialTheme.typography.displayMedium.copy(
            fontWeight = FontWeight.Black,
            color = Color.White
          )
        )
        Text(
          text = "/$period",
          style = MaterialTheme.typography.bodyMedium.copy(
            color = Color(0xFF94A3B8)
          ),
          modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )
      }

      Spacer(modifier = Modifier.height(20.dp))
      Divider(color = Color(0xFF334155))
      Spacer(modifier = Modifier.height(20.dp))

      // Features list
      Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        features.forEach { feature ->
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = null,
              tint = if (isPopular) Color(0xFF38BDF8) else Color(0xFF10B981),
              modifier = Modifier
                .padding(top = 2.dp)
                .size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = feature,
              style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFFE2E8F0)
              )
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(28.dp))

      // Button CTA
      Button(
        onClick = onSelect,
        colors = ButtonDefaults.buttonColors(
          containerColor = if (isPopular) Color(0xFFC084FC) else Color(0xFF0F172A)
        ),
        shape = RoundedCornerShape(12.dp),
        border = if (!isPopular) BorderStroke(1.dp, Color(0xFF334155)) else null,
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
      ) {
        Text(
          text = ctaText,
          style = MaterialTheme.typography.labelLarge.copy(
            color = if (isPopular) Color(0xFF0F172A) else Color.White,
            fontWeight = FontWeight.Bold
          )
        )
      }
    }
  }
}

// Free Trial Highlight Section emphasizing "No Credit Card Required"
@Composable
fun FreeTrialHighlightSection(
  onStartTrialClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 56.dp)
      .testTag("free_trial_highlight_section"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Top Accent Badge
    Box(
      modifier = Modifier
        .background(
          brush = Brush.linearGradient(
            colors = listOf(Color(0xFFF43F5E), Color(0xFFC084FC))
          ),
          shape = RoundedCornerShape(20.dp)
        )
        .padding(horizontal = 14.dp, vertical = 6.dp)
        .testTag("no_credit_card_badge")
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Sell,
          contentDescription = null,
          tint = Color.White,
          modifier = Modifier.size(14.dp)
        )
        Text(
          text = "No Credit Card Required",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp
          ),
          color = Color.White
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Heading
    Text(
      text = "Get 14 Days of Premium Designing",
      style = MaterialTheme.typography.headlineMedium.copy(
        fontWeight = FontWeight.Black,
        textAlign = TextAlign.Center
      ),
      color = Color.White
    )
    
    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = "Unleash high-res vector downloads, complete color styling presets, and expert generative suggestions instantly.",
      style = MaterialTheme.typography.bodyMedium,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier.widthIn(max = 500.dp)
    )

    Spacer(modifier = Modifier.height(32.dp))

    // Layout Depth: Overlapping visual card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 600.dp)
        .border(
          width = 1.dp,
          brush = Brush.radialGradient(
            colors = listOf(Color(0xFF38BDF8), Color(0x00FFFFFF)),
            radius = 600f
          ),
          shape = RoundedCornerShape(24.dp)
        ),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
      shape = RoundedCornerShape(24.dp),
      elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
      ) {
        // Feature grid or key benefits
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          TrialBenefitRow(
            icon = Icons.Default.CheckCircle,
            title = "Zero Financial Friction",
            desc = "Start immediately with zero commitments. No credit card, no debit card, no bank account details required."
          )
          TrialBenefitRow(
            icon = Icons.Default.CheckCircle,
            title = "100% Unlimited Drafts",
            desc = "Create, iterate, tweak colors, customize layouts, and experiment with as many logos and posters as you want."
          )
          TrialBenefitRow(
            icon = Icons.Default.CheckCircle,
            title = "Instant Export Download",
            desc = "Download ready-to-use PNG designs for layouts or previews immediately without paying a single cent."
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // CTA Button
        Button(
          onClick = onStartTrialClick,
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF38BDF8)
          ),
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("claim_free_trial_btn")
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
          ) {
            Text(
              text = "Claim Your Free 14-Day Trial",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
              )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
              imageVector = Icons.Default.ArrowForward,
              contentDescription = null,
              tint = Color(0xFF0F172A),
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Subtext / Security Reassurance
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
          modifier = Modifier.fillMaxWidth()
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = Color(0xFF34D399),
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Risk-Free • Instant Onboarding • No Credit Card Ever",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
            color = Color(0xFF34D399)
          )
        }
      }
    }
  }
}

@Composable
fun TrialBenefitRow(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  title: String,
  desc: String
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.Top
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = Color(0xFF38BDF8),
      modifier = Modifier
        .size(24.dp)
        .padding(top = 2.dp)
    )
    Spacer(modifier = Modifier.width(16.dp))
    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
        color = Color.White
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = desc,
        style = MaterialTheme.typography.bodySmall,
        color = Color(0xFF94A3B8),
        lineHeight = 18.sp
      )
    }
  }
}

// FAQ Section
@Composable
fun FaqSection() {
  val faqData = listOf(
    Pair("Is the trial really free?", "Absolutely. You can play with all templates, customize names, change aesthetics, and download draft versions without ever typing a credit card."),
    Pair("Can I use these designs commercially?", "Yes! Once you subscribe to a paid Creator or Pro plan (starting at super-affordable rates), you gain full commercial rights to use them on physical merchandise, storefronts, and online advertising."),
    Pair("How much does a single design cost?", "Our plans give you unlimited access for less than a single freelance design iteration. Starting under $10, it's cheaper than any freelancer on the market!"),
    Pair("Do you provide vector format?", "Yes, the Pro Brand Plan provides unlimited high-res vector outputs (SVG and PDF formats) perfect for massive scale printing and high-end merchandising.")
  )

  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 48.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Frequently Asked Questions",
      style = MaterialTheme.typography.displayMedium,
      color = Color.White,
      textAlign = TextAlign.Center
    )
    Text(
      text = "Still have questions? Here is how we simplify instant design.",
      style = MaterialTheme.typography.bodyLarge,
      color = Color(0xFF94A3B8),
      textAlign = TextAlign.Center,
      modifier = Modifier
        .padding(top = 8.dp, bottom = 40.dp)
        .widthIn(max = 520.dp)
    )

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 700.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      faqData.forEach { (question, answer) ->
        FaqAccordionCard(question = question, answer = answer)
      }
    }
  }
}

// Individual FAQ Accordion Card Composable
@Composable
fun FaqAccordionCard(question: String, answer: String) {
  var isExpanded by remember { mutableStateOf(false) }
  val angleState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { isExpanded = !isExpanded }
      .animateContentSize()
      .testTag("faq_card"),
    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.05f)),
    shape = RoundedCornerShape(14.dp),
    border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f))
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = question,
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          ),
          modifier = Modifier.weight(1f)
        )
        IconButton(
          onClick = { isExpanded = !isExpanded },
          modifier = Modifier.size(24.dp)
        ) {
          Icon(
            imageVector = Icons.Default.ExpandMore,
            contentDescription = "Expand FAQ",
            tint = Color(0xFF38BDF8),
            modifier = Modifier.rotate(angleState)
          )
        }
      }

      AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
      ) {
        Column {
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = answer,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = Color(0xFF94A3B8),
              lineHeight = 22.sp
            )
          )
        }
      }
    }
  }
}

// Footer Section Composable
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
  onNavigate: (String) -> Unit,
  onTryFree: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 40.dp)
      .navigationBarsPadding(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Brandcraft final CTA Block
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 900.dp)
        .padding(bottom = 32.dp),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
      shape = RoundedCornerShape(24.dp),
      border = BorderStroke(1.dp, Color(0xFF334155))
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "Ready to launch your brand?",
          style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = FontWeight.Black,
            color = Color.White
          ),
          textAlign = TextAlign.Center
        )
        Text(
          text = "Create amazing graphics in under 2 minutes. Free trial included. No card required.",
          style = MaterialTheme.typography.bodyMedium,
          color = Color(0xFF94A3B8),
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        Button(
          onClick = onTryFree,
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.height(48.dp)
        ) {
          Text(
            text = "Try It Free Now",
            style = MaterialTheme.typography.labelLarge.copy(
              color = Color(0xFF0F172A),
              fontWeight = FontWeight.Black
            )
          )
        }
      }
    }

    // Links & Legal
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 900.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Left: Logo + copyright
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(24.dp)
            .background(
              brush = Brush.linearGradient(
                colors = listOf(Color(0xFF38BDF8), Color(0xFFC084FC))
              ),
              shape = RoundedCornerShape(6.dp)
            ),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Palette,
            contentDescription = null,
            tint = Color(0xFF0F172A),
            modifier = Modifier.size(12.dp)
          )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "BrandCraft",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        )
      }

      // Center navigation links
      FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.Center
      ) {
        val footerLinks = listOf(
          "Features" to "features",
          "Gallery" to "gallery",
          "Pricing" to "pricing",
          "FAQ" to "faq"
        )
        footerLinks.forEach { (label, dest) ->
          Text(
            text = label,
            modifier = Modifier
              .clickable { onNavigate(dest) }
              .padding(vertical = 4.dp),
            style = MaterialTheme.typography.bodyMedium.copy(
              fontWeight = FontWeight.Medium,
              color = Color(0xFF94A3B8)
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "© 2026 BrandCraft Inc. All rights reserved. Design made in under 2 minutes.",
      style = MaterialTheme.typography.bodySmall.copy(
        color = Color(0xFF64748B),
        fontSize = 11.sp
      ),
      textAlign = TextAlign.Center
    )
  }
}

// Dialog: Login Form
@Composable
fun LoginFormDialog(
  onDismiss: () -> Unit,
  onLoginSuccess: (String) -> Unit
) {
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var emailError by remember { mutableStateOf<String?>(null) }
  var passwordError by remember { mutableStateOf<String?>(null) }
  val context = LocalContext.current

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .testTag("login_dialog_card"),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
      shape = RoundedCornerShape(24.dp),
      border = BorderStroke(1.dp, Color(0xFF334155))
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Close Button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.End
        ) {
          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(36.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = Color(0xFF94A3B8)
            )
          }
        }

        // Header
        Box(
          modifier = Modifier
            .size(48.dp)
            .background(Color(0xFF1E1B4B), shape = CircleShape)
            .border(BorderStroke(1.dp, Color(0xFF4338CA)), shape = CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Palette,
            contentDescription = null,
            tint = Color(0xFF818CF8),
            modifier = Modifier.size(24.dp)
          )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          text = "Welcome to BrandCraft",
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
          color = Color.White,
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "Sign in to sync your design library and unlock advanced prompt suggestions.",
          style = MaterialTheme.typography.bodySmall,
          color = Color(0xFF94A3B8),
          textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Email Field
        Column(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "Email Address",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.padding(bottom = 6.dp)
          )
          OutlinedTextField(
            value = email,
            onValueChange = {
              email = it
              emailError = null
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("login_email_input"),
            placeholder = { Text("e.g. you@company.com", color = Color(0xFF64748B)) },
            singleLine = true,
            isError = emailError != null,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White,
              focusedBorderColor = Color(0xFF38BDF8),
              unfocusedBorderColor = Color(0xFF334155),
              focusedContainerColor = Color(0xFF0F172A),
              unfocusedContainerColor = Color(0xFF0F172A)
            )
          )
          emailError?.let {
            Text(
              text = it,
              style = MaterialTheme.typography.labelSmall,
              color = Color(0xFFEF4444),
              modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        Column(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "Password",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier.padding(bottom = 6.dp)
          )
          OutlinedTextField(
            value = password,
            onValueChange = {
              password = it
              passwordError = null
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("login_password_input"),
            placeholder = { Text("Enter your password", color = Color(0xFF64748B)) },
            singleLine = true,
            isError = passwordError != null,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White,
              focusedBorderColor = Color(0xFF38BDF8),
              unfocusedBorderColor = Color(0xFF334155),
              focusedContainerColor = Color(0xFF0F172A),
              unfocusedContainerColor = Color(0xFF0F172A)
            )
          )
          passwordError?.let {
            Text(
              text = it,
              style = MaterialTheme.typography.labelSmall,
              color = Color(0xFFEF4444),
              modifier = Modifier.padding(top = 4.dp, start = 4.dp)
            )
          }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Button
        Button(
          onClick = {
            var hasError = false
            if (email.isBlank()) {
              emailError = "Email is required"
              hasError = true
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
              emailError = "Please enter a valid email"
              hasError = true
            }
            if (password.isBlank()) {
              passwordError = "Password is required"
              hasError = true
            } else if (password.length < 6) {
              passwordError = "Password must be at least 6 characters"
              hasError = true
            }

            if (!hasError) {
              onLoginSuccess(email)
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("login_submit_btn")
        ) {
          Text(
            text = "Sign In",
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF0F172A)
          )
        }
      }
    }
  }
}

// Dialog: Free Trial Request Form
@Composable
fun FreeTrialFormDialog(
  onDismiss: () -> Unit,
  onSubmit: (String, String) -> Unit
) {
  var emailInput by remember { mutableStateOf("") }
  var businessNameInput by remember { mutableStateOf("") }
  var isAgreed by remember { mutableStateOf(true) }
  var isSubmitting by remember { mutableStateOf(false) }

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .testTag("free_trial_dialog"),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF13151B).copy(alpha = 0.95f)),
      shape = RoundedCornerShape(24.dp),
      border = BorderStroke(1.dp, Color.White.copy(alpha = 0.15f))
    ) {
      Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Create Free Account",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.Black,
              color = Color.White
            )
          )
          IconButton(onClick = onDismiss) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = Color(0xFF94A3B8)
            )
          }
        }

        Text(
          text = "No credit card required. Start creating professional posters and logos immediately.",
          style = MaterialTheme.typography.bodyMedium,
          color = Color(0xFF94A3B8),
          modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
        )

        // Email Text Field
        OutlinedTextField(
          value = emailInput,
          onValueChange = { emailInput = it },
          label = { Text("Business Email") },
          modifier = Modifier
            .fillMaxWidth()
            .testTag("dialog_email_field"),
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = Color(0xFF38BDF8),
            unfocusedBorderColor = Color(0xFF334155),
            focusedContainerColor = Color(0xFF0F172A),
            unfocusedContainerColor = Color(0xFF0F172A)
          )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Business Name Text Field
        OutlinedTextField(
          value = businessNameInput,
          onValueChange = { businessNameInput = it },
          label = { Text("Brand / Business Name") },
          modifier = Modifier
            .fillMaxWidth()
            .testTag("dialog_biz_field"),
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = Color(0xFF38BDF8),
            unfocusedBorderColor = Color(0xFF334155),
            focusedContainerColor = Color(0xFF0F172A),
            unfocusedContainerColor = Color(0xFF0F172A)
          )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Legal check
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            tint = Color(0xFF38BDF8),
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Agree to Free Trial & Commercial Terms",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF94A3B8)
          )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Submit Button with simulation loader
        Button(
          onClick = {
            if (emailInput.isNotBlank()) {
              isSubmitting = true
              onSubmit(emailInput, businessNameInput)
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("dialog_submit_btn"),
          enabled = !isSubmitting && emailInput.isNotBlank()
        ) {
          if (isSubmitting) {
            Text(
              text = "Initializing Editor...",
              style = MaterialTheme.typography.labelLarge.copy(color = Color(0xFF0F172A))
            )
          } else {
            Text(
              text = "Generate My Graphics",
              style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF0F172A),
                fontWeight = FontWeight.Black
              )
            )
          }
        }
      }
    }
  }
}

@Composable
fun UpiPaymentDialog(
  planName: String,
  price: String,
  onDismiss: () -> Unit,
  onPaymentSuccess: (String) -> Unit
) {
  var upiIdInput by remember { mutableStateOf("") }
  var isUpiValid by remember { mutableStateOf(true) }
  var selectedApp by remember { mutableStateOf<String?>(null) }
  var paymentStep by remember { mutableStateOf("methods") } // "methods", "processing", "success"
  var transactionId by remember { mutableStateOf("") }
  var countdownSeconds by remember { mutableStateOf(300) } // 5 mins
  var showQrCode by remember { mutableStateOf(false) }

  // Countdown timer for VPA/QR
  LaunchedEffect(showQrCode, paymentStep) {
    if (showQrCode && paymentStep == "methods" && countdownSeconds > 0) {
      while (countdownSeconds > 0) {
        kotlinx.coroutines.delay(1000L)
        countdownSeconds--
      }
    }
  }

  // Calculate equivalent price in Indian Rupees
  val priceInInr = when {
    price.contains("7") -> "₹599"
    price.contains("9") -> "₹750"
    price.contains("15") -> "₹1,250"
    price.contains("19") -> "₹1,599"
    else -> "₹750"
  }

  val formattedTime = String.format("%02d:%02d", countdownSeconds / 60, countdownSeconds % 60)

  Dialog(onDismissRequest = { if (paymentStep != "processing") onDismiss() }) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 16.dp)
        .testTag("upi_payment_dialog"),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
      shape = RoundedCornerShape(28.dp),
      border = BorderStroke(1.dp, Color(0xFF38BDF8).copy(alpha = 0.3f))
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        if (paymentStep == "methods") {
          // Header
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "UPI Secure Checkout",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Black,
                  letterSpacing = 0.5.sp
                ),
                color = Color.White
              )
              Text(
                text = "Upgrade to $planName",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF38BDF8)
              )
            }
            IconButton(onClick = onDismiss) {
              Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Checkout",
                tint = Color(0xFF94A3B8)
              )
            }
          }

          Spacer(modifier = Modifier.height(20.dp))

          // Pricing Summary Banner
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0xFF1E293B), shape = RoundedCornerShape(16.dp))
              .border(BorderStroke(1.dp, Color(0xFF334155)), shape = RoundedCornerShape(16.dp))
              .padding(16.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "TOTAL AMOUNT DUE",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
                  color = Color(0xFF94A3B8)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = priceInInr,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black),
                    color = Color.White
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = "($price USD)",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF94A3B8)
                  )
                }
              }
              Box(
                modifier = Modifier
                  .background(Color(0x2210B981), shape = RoundedCornerShape(8.dp))
                  .border(BorderStroke(1.dp, Color(0xFF10B981)), shape = RoundedCornerShape(8.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(
                  text = "SECURE UPI",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black),
                  color = Color(0xFF10B981)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(20.dp))

          // Toggle QR / App view
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0xFF1E293B), shape = RoundedCornerShape(12.dp))
              .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            // Pay via UPI App / ID
            Box(
              modifier = Modifier
                .weight(1f)
                .background(
                  color = if (!showQrCode) Color(0xFF0F172A) else Color.Transparent,
                  shape = RoundedCornerShape(8.dp)
                )
                .clickable { showQrCode = false }
                .padding(vertical = 10.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "UPI Apps & ID",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = if (!showQrCode) Color.White else Color(0xFF94A3B8)
              )
            }

            // Pay via QR Code Scan
            Box(
              modifier = Modifier
                .weight(1f)
                .background(
                  color = if (showQrCode) Color(0xFF0F172A) else Color.Transparent,
                  shape = RoundedCornerShape(8.dp)
                )
                .clickable { showQrCode = true }
                .padding(vertical = 10.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "Scan QR Code",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = if (showQrCode) Color.White else Color(0xFF94A3B8)
              )
            }
          }

          Spacer(modifier = Modifier.height(18.dp))

          if (!showQrCode) {
            // UPI Apps selection grid
            Text(
              text = "Pay using your preferred UPI app:",
              style = MaterialTheme.typography.labelMedium,
              color = Color(0xFF94A3B8),
              modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              UpiAppButton(
                appName = "Google Pay",
                appColor = Color(0xFF4285F4),
                isSelected = selectedApp == "Google Pay",
                onClick = {
                  selectedApp = "Google Pay"
                  upiIdInput = ""
                },
                modifier = Modifier.weight(1f)
              )
              UpiAppButton(
                appName = "PhonePe",
                appColor = Color(0xFF5F259F),
                isSelected = selectedApp == "PhonePe",
                onClick = {
                  selectedApp = "PhonePe"
                  upiIdInput = ""
                },
                modifier = Modifier.weight(1f)
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              UpiAppButton(
                appName = "Paytm",
                appColor = Color(0xFF00B9F5),
                isSelected = selectedApp == "Paytm",
                onClick = {
                  selectedApp = "Paytm"
                  upiIdInput = ""
                },
                modifier = Modifier.weight(1f)
              )
              UpiAppButton(
                appName = "BHIM UPI",
                appColor = Color(0xFFE67E22),
                isSelected = selectedApp == "BHIM UPI",
                onClick = {
                  selectedApp = "BHIM UPI"
                  upiIdInput = ""
                },
                modifier = Modifier.weight(1f)
              )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Or enter manual UPI VPA ID
            Text(
              text = "Or enter your UPI ID (VPA):",
              style = MaterialTheme.typography.labelMedium,
              color = Color(0xFF94A3B8),
              modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
              value = upiIdInput,
              onValueChange = {
                upiIdInput = it
                selectedApp = null
                isUpiValid = true
              },
              placeholder = { Text("e.g. username@upi", color = Color(0xFF64748B)) },
              isError = !isUpiValid,
              modifier = Modifier
                .fillMaxWidth()
                .testTag("upi_id_text_field"),
              shape = RoundedCornerShape(12.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedContainerColor = Color(0xFF1E293B),
                unfocusedContainerColor = Color(0xFF1E293B)
              ),
              singleLine = true
            )

            if (!isUpiValid) {
              Text(
                text = "Please enter a valid UPI ID (e.g. mobile@ybl, name@oksbi)",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFEF4444),
                modifier = Modifier
                  .align(Alignment.Start)
                  .padding(top = 4.dp, start = 4.dp)
              )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Pay Button
            Button(
              onClick = {
                if (selectedApp != null) {
                  // Simulate app payment trigger
                  paymentStep = "processing"
                } else {
                  val trimmed = upiIdInput.trim()
                  if (trimmed.contains("@") && trimmed.length >= 5) {
                    paymentStep = "processing"
                  } else {
                    isUpiValid = false
                  }
                }
              },
              enabled = selectedApp != null || upiIdInput.isNotBlank(),
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("pay_upi_submit_btn")
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Proceed to Pay $priceInInr",
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                  color = Color.White
                )
              }
            }
          } else {
            // QR Code scan mode
            val context = androidx.compose.ui.platform.LocalContext.current
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(
                text = "Scan this QR code using Google Pay, PhonePe, Paytm, or any UPI App to pay securely:",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF94A3B8),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 10.dp)
              )

              Spacer(modifier = Modifier.height(20.dp))

              // Custom styled QR Card matching the user's uploaded layout
              Box(
                modifier = Modifier
                  .width(240.dp)
                  .background(Color(0xFF2E2219), shape = RoundedCornerShape(24.dp))
                  .border(BorderStroke(1.dp, Color(0xFFE28A2B).copy(alpha = 0.3f)), shape = RoundedCornerShape(24.dp))
                  .padding(top = 28.dp, bottom = 28.dp, start = 24.dp, end = 24.dp)
                  .clickable {
                    // Clicking QR Code also lets the user easily simulate successful payment
                    paymentStep = "processing"
                  },
                contentAlignment = Alignment.Center
              ) {
                Column(
                  horizontalAlignment = Alignment.CenterHorizontally,
                  verticalArrangement = Arrangement.Center
                ) {
                  // The actual QR code matrix
                  Box(
                    modifier = Modifier
                      .aspectRatio(1f)
                      .background(Color(0xFFECE5DA), shape = RoundedCornerShape(16.dp))
                      .padding(14.dp),
                    contentAlignment = Alignment.Center
                  ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                      val sizePx = size.width
                      val blockCount = 17
                      val blockSize = sizePx / blockCount
                      val qrColor = Color(0xFF2E2219)
                      val bgColor = Color(0xFFECE5DA)

                      // 1. Draw Position Detection Patterns
                      // Helper function for locators
                      fun drawLocator(x: Float, y: Float) {
                        // Outer square: 6 x 6 blocks
                        drawRect(
                          color = qrColor,
                          topLeft = androidx.compose.ui.geometry.Offset(x, y),
                          size = androidx.compose.ui.geometry.Size(blockSize * 6, blockSize * 6)
                        )
                        // Inner spacer: 4 x 4 blocks
                        drawRect(
                          color = bgColor,
                          topLeft = androidx.compose.ui.geometry.Offset(x + blockSize, y + blockSize),
                          size = androidx.compose.ui.geometry.Size(blockSize * 4, blockSize * 4)
                        )
                        // Center square: 2 x 2 blocks
                        drawRect(
                          color = qrColor,
                          topLeft = androidx.compose.ui.geometry.Offset(x + blockSize * 2, y + blockSize * 2),
                          size = androidx.compose.ui.geometry.Size(blockSize * 2, blockSize * 2)
                        )
                      }

                      // Top-left
                      drawLocator(0f, 0f)
                      // Top-right
                      drawLocator(blockSize * (blockCount - 6), 0f)
                      // Bottom-left
                      drawLocator(0f, blockSize * (blockCount - 6))

                      // 2. Draw random/deterministic blocks (excluding corner locators & center area)
                      for (i in 0 until blockCount) {
                        for (j in 0 until blockCount) {
                          // Skip top-left locator
                          if (i < 6 && j < 6) continue
                          // Skip top-right locator
                          if (i >= blockCount - 6 && j < 6) continue
                          // Skip bottom-left locator
                          if (i < 6 && j >= blockCount - 6) continue
                          // Skip center logo area (columns 7..9, rows 7..9)
                          if (i in 7..9 && j in 7..9) continue

                          // Pseudo-random deterministic formula for genuine QR look
                          val isFilled = ((i * 31 + j * 17) % 3 == 0) || ((i * j + 5) % 4 == 1)
                          if (isFilled) {
                            drawRect(
                              color = qrColor,
                              topLeft = androidx.compose.ui.geometry.Offset(i * blockSize, j * blockSize),
                              size = androidx.compose.ui.geometry.Size(blockSize, blockSize)
                            )
                          }
                        }
                      }

                      // 3. Draw central Phoenix bird logo badge (matching the user's uploaded image logo)
                      val centerPx = sizePx / 2
                      val centerPy = sizePx / 2
                      val circleRadius = blockSize * 2.2f

                      // Logo outer circle background
                      drawCircle(
                        color = qrColor,
                        radius = circleRadius,
                        center = androidx.compose.ui.geometry.Offset(centerPx, centerPy)
                      )
                      // Logo border
                      drawCircle(
                        color = bgColor,
                        radius = circleRadius,
                        center = androidx.compose.ui.geometry.Offset(centerPx, centerPy),
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx())
                      )

                      // Golden stylized flying bird logo
                      val birdGold = Color(0xFFF59E0B)
                      val wingPath = Path().apply {
                        // Wing curving upwards
                        moveTo(centerPx - blockSize * 0.7f, centerPy + blockSize * 0.3f)
                        cubicTo(
                          centerPx - blockSize * 0.6f, centerPy - blockSize * 0.1f,
                          centerPx - blockSize * 0.1f, centerPy - blockSize * 0.6f,
                          centerPx + blockSize * 0.4f, centerPy - blockSize * 0.7f
                        )
                        cubicTo(
                          centerPx + blockSize * 0.1f, centerPy - blockSize * 0.2f,
                          centerPx - blockSize * 0.1f, centerPy + blockSize * 0.1f,
                          centerPx - blockSize * 0.7f, centerPy + blockSize * 0.3f
                        )
                      }
                      drawPath(wingPath, color = birdGold)

                      val tailPath = Path().apply {
                        // Wing body and tail
                        moveTo(centerPx - blockSize * 0.5f, centerPy + blockSize * 0.2f)
                        cubicTo(
                          centerPx + blockSize * 0.1f, centerPy + blockSize * 0.2f,
                          centerPx + blockSize * 0.5f, centerPy - blockSize * 0.2f,
                          centerPx + blockSize * 0.7f, centerPy - blockSize * 0.4f
                        )
                        cubicTo(
                          centerPx + blockSize * 0.4f, centerPy + blockSize * 0.1f,
                          centerPx + blockSize * 0.0f, centerPy + blockSize * 0.4f,
                          centerPx - blockSize * 0.5f, centerPy + blockSize * 0.2f
                        )
                      }
                      drawPath(tailPath, color = birdGold)
                    }
                  }
                }
              }

              // Capsule Pill for clipboard copying and sharing under the QR card
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(top = 20.dp, bottom = 12.dp)
              ) {
                // Display UPI ID capsule pill
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  modifier = Modifier
                    .background(Color(0xFF1E293B), shape = RoundedCornerShape(24.dp))
                    .border(BorderStroke(1.dp, Color(0xFF334155)), shape = RoundedCornerShape(24.dp))
                    .padding(start = 16.dp, end = 8.dp, top = 6.dp, bottom = 6.dp)
                ) {
                  Text(
                    text = "9025819743@fam",
                    style = MaterialTheme.typography.bodyMedium.copy(
                      fontWeight = FontWeight.Bold,
                      letterSpacing = 0.5.sp
                    ),
                    color = Color.White
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  IconButton(
                    onClick = {
                      val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                      val clip = android.content.ClipData.newPlainText("BrandCraft UPI ID", "9025819743@fam")
                      clipboard.setPrimaryClip(clip)
                      android.widget.Toast.makeText(context, "UPI ID copied to clipboard!", android.widget.Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.size(32.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Default.ContentCopy,
                      contentDescription = "Copy UPI ID",
                      tint = Color(0xFF38BDF8),
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Share button
                IconButton(
                  onClick = {
                    val shareIntent = android.content.Intent(android.content.Intent.ACTION_SEND).apply {
                      type = "text/plain"
                      putExtra(android.content.Intent.EXTRA_TEXT, "Pay securely via UPI to BrandCraft: 9025819743@fam")
                    }
                    context.startActivity(android.content.Intent.createChooser(shareIntent, "Share UPI ID"))
                  },
                  modifier = Modifier
                    .background(Color(0xFF1E293B), shape = CircleShape)
                    .border(BorderStroke(1.dp, Color(0xFF334155)), shape = CircleShape)
                    .size(36.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share UPI ID",
                    tint = Color(0xFF38BDF8),
                    modifier = Modifier.size(16.dp)
                  )
                }
              }

              // Pulse scanner indicator
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Icon(
                  imageVector = Icons.Default.Info,
                  contentDescription = null,
                  tint = Color(0xFFE67E22),
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "QR Code expires in $formattedTime",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = Color(0xFFE67E22)
                )
              }

              Spacer(modifier = Modifier.height(18.dp))

              // Click to simulate payment scan
              Button(
                onClick = { paymentStep = "processing" },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF38BDF8)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
              ) {
                Text(
                  text = "Simulate QR Scan Success",
                  style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                  )
                )
              }
            }
          }
        } else if (paymentStep == "processing") {
          // Pulse loader animation
          var loadingMessage by remember { mutableStateOf("Contacting UPI gateway...") }

          LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1000L)
            loadingMessage = "Verifying VPA transaction details..."
            kotlinx.coroutines.delay(1000L)
            loadingMessage = "Waiting for bank settlement..."
            kotlinx.coroutines.delay(1000L)
            transactionId = "UPI" + (100000000000L..999999999999L).random().toString()
            paymentStep = "success"
          }

          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            CircularProgressIndicator(
              color = Color(0xFF38BDF8),
              modifier = Modifier.size(56.dp),
              strokeWidth = 4.dp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
              text = "Securing Connection",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = loadingMessage,
              style = MaterialTheme.typography.bodySmall,
              color = Color(0xFF94A3B8),
              textAlign = TextAlign.Center
            )
          }
        } else if (paymentStep == "success") {
          // Success Screen
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Box(
              modifier = Modifier
                .size(72.dp)
                .background(Color(0xFF10B981).copy(alpha = 0.2f), shape = RoundedCornerShape(36.dp))
                .border(BorderStroke(2.dp, Color(0xFF10B981)), shape = RoundedCornerShape(36.dp)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF10B981),
                modifier = Modifier.size(44.dp)
              )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
              text = "Payment Completed!",
              style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
              color = Color.White
            )
            Text(
              text = "Your $planName is now active",
              style = MaterialTheme.typography.bodyMedium,
              color = Color(0xFF34D399)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Receipts metadata card
            Card(
              modifier = Modifier.fillMaxWidth(),
              colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
              shape = RoundedCornerShape(16.dp),
              border = BorderStroke(1.dp, Color(0xFF334155))
            ) {
              Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                ReceiptRow(label = "Plan Purchased", value = planName)
                ReceiptRow(label = "Amount Paid", value = "$priceInInr ($price USD)")
                ReceiptRow(label = "Payment Method", value = "UPI Secured Payment")
                ReceiptRow(label = "Transaction ID", value = transactionId)
              }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
              onClick = { onPaymentSuccess(transactionId) },
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("upi_payment_done_btn")
            ) {
              Text(
                text = "Unleash Premium Power",
                style = MaterialTheme.typography.labelLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun UpiAppButton(
  appName: String,
  appColor: Color,
  isSelected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .background(
        color = if (isSelected) appColor.copy(alpha = 0.15f) else Color(0xFF1E293B),
        shape = RoundedCornerShape(12.dp)
      )
      .border(
        width = 1.dp,
        color = if (isSelected) appColor else Color(0xFF334155),
        shape = RoundedCornerShape(12.dp)
      )
      .clickable(onClick = onClick)
      .padding(horizontal = 12.dp, vertical = 14.dp),
    contentAlignment = Alignment.Center
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(10.dp)
          .background(appColor, shape = RoundedCornerShape(5.dp))
      )
      Text(
        text = appName,
        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
        color = Color.White
      )
    }
  }
}

@Composable
fun ReceiptRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = label,
      style = MaterialTheme.typography.labelSmall,
      color = Color(0xFF94A3B8)
    )
    Text(
      text = value,
      style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
      color = Color.White
    )
  }
}

// Celebration Confetti simulation dialog upon click download
@Composable
fun SimulatedCelebrationDialog(
  title: String,
  type: String,
  onDismiss: () -> Unit
) {
  var progress by remember { mutableStateOf(0f) }
  var downloadComplete by remember { mutableStateOf(false) }

  // Simulate download progress bar
  LaunchedEffect(Unit) {
    while (progress < 1.0f) {
      delay(Random.nextLong(20, 50))
      progress += Random.nextFloat() * 0.15f
      if (progress > 1.0f) progress = 1.0f
    }
    downloadComplete = true
  }

  Dialog(onDismissRequest = onDismiss) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .testTag("celebration_dialog"),
      colors = CardDefaults.cardColors(containerColor = Color(0xFF13151A).copy(alpha = 0.95f)),
      shape = RoundedCornerShape(24.dp),
      border = BorderStroke(2.dp, Brush.linearGradient(listOf(Color(0xFF7C3AED), Color(0xFF60A5FA))))
    ) {
      Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        if (!downloadComplete) {
          Icon(
            imageVector = Icons.Default.CloudDownload,
            contentDescription = null,
            tint = Color(0xFF38BDF8),
            modifier = Modifier
              .size(52.dp)
              .padding(bottom = 12.dp)
          )

          Text(
            text = "Exporting High-Res Assets",
            style = MaterialTheme.typography.headlineMedium.copy(
              fontWeight = FontWeight.ExtraBold,
              color = Color.White
            )
          )
          Text(
            text = "Converting draft '$title' to high-resolution print vectors...",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF94A3B8),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
          )

          Spacer(modifier = Modifier.height(16.dp))

          // Elegant Progress representation
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(12.dp)
              .background(Color(0xFF1E293B), shape = RoundedCornerShape(6.dp))
              .clip(RoundedCornerShape(6.dp))
          ) {
            Box(
              modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(
                  Brush.horizontalGradient(listOf(Color(0xFF38BDF8), Color(0xFFC084FC)))
                )
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "${(progress * 100).toInt()}% Rendered",
            style = MaterialTheme.typography.labelSmall.copy(
              color = Color(0xFF38BDF8),
              fontWeight = FontWeight.Bold
            )
          )
        } else {
          // Success celebration
          Box(
            modifier = Modifier
              .size(72.dp)
              .background(Color(0x3310B981), shape = CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = "Success",
              tint = Color(0xFF10B981),
              modifier = Modifier.size(36.dp)
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "Design Ready!",
            style = MaterialTheme.typography.displayMedium.copy(
              fontSize = 24.sp,
              fontWeight = FontWeight.Black,
              color = Color.White
            )
          )
          Text(
            text = "Simulated high-res file download completed successfully for $type '$title'!",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFE2E8F0),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 6.dp, bottom = 20.dp)
          )

          // Glowing border message box
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .background(Color(0x2238BDF8), shape = RoundedCornerShape(12.dp))
              .border(1.dp, Color(0xFF38BDF8).copy(alpha = 0.5f), shape = RoundedCornerShape(12.dp))
              .padding(14.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color(0xFF38BDF8),
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = "Commercial rights are fully unlocked! No watermarks. Scale it safely to billboard or sticker size.",
                style = MaterialTheme.typography.bodySmall.copy(
                  color = Color(0xFFE2E8F0),
                  lineHeight = 16.sp
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(24.dp))

          Button(
            onClick = onDismiss,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(44.dp)
              .testTag("celebration_done_btn")
          ) {
            Text(
              text = "Excellent!",
              style = MaterialTheme.typography.labelLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
              )
            )
          }
        }
      }
    }
  }
}
